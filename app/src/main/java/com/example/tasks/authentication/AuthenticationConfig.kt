package com.example.tasks.authentication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.auth0.android.jwt.JWT
import com.example.tasks.common.data.preferences.Preferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.openid.appauth.AppAuthConfiguration
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues
import net.openid.appauth.browser.BrowserAllowList
import net.openid.appauth.browser.VersionedBrowserMatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONException
import org.json.JSONObject
import java.security.MessageDigest
import java.security.SecureRandom
import javax.inject.Inject

class AuthenticationConfig @Inject constructor(
    private val preferences: Preferences,
    @ApplicationContext private val context: Context
) {

    var authState: AuthState = AuthState()
    var jwt: JWT? = null
    lateinit var authorizationService: AuthorizationService
    lateinit var authServiceConfig: AuthorizationServiceConfiguration

    init {
        restoreState()
        initAuthServiceConfig()
        initAuthService()
    }

    private fun restoreState() {

        val jsonString = preferences.getAuthState()

        if (jsonString.isNotEmpty() && !TextUtils.isEmpty(jsonString)) {
            try {
                authState = AuthState.jsonDeserialize(jsonString)

                if (!TextUtils.isEmpty(authState.idToken)) {
                    jwt = JWT(authState.idToken!!)
                }

            } catch (jsonException: JSONException) {
            }
        }
    }

    private fun persistState() {
        preferences.putAuthState(authState.jsonSerializeString())
        preferences.putToken(authState.accessToken.orEmpty())
    }

    private fun initAuthServiceConfig() {
        authServiceConfig = AuthorizationServiceConfiguration(
            Uri.parse(AuthenticationHelper.URL_AUTHORIZATION),
            Uri.parse(AuthenticationHelper.URL_TOKEN_EXCHANGE),
            null,
            Uri.parse(AuthenticationHelper.URL_LOGOUT)
        )
    }

    private fun initAuthService() {
        val appAuthConfiguration = AppAuthConfiguration.Builder()
            .setBrowserMatcher(
                BrowserAllowList(
                    VersionedBrowserMatcher.CHROME_CUSTOM_TAB,
                    VersionedBrowserMatcher.SAMSUNG_CUSTOM_TAB
                )
            ).build()

        authorizationService = AuthorizationService(
            context,
            appAuthConfiguration
        )
    }

    fun attemptAuthorization(): Intent {

        val secureRandom = SecureRandom()
        val bytes = ByteArray(64)
        secureRandom.nextBytes(bytes)

        val encoding = Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP
        val codeVerifier = Base64.encodeToString(bytes, encoding)

        val digest = MessageDigest.getInstance(AuthenticationHelper.MESSAGE_DIGEST_ALGORITHM)
        val hash = digest.digest(codeVerifier.toByteArray())
        val codeChallenge = Base64.encodeToString(hash, encoding)


        val builder = AuthorizationRequest.Builder(
            authServiceConfig,
            AuthenticationHelper.CLIENT_ID,
            ResponseTypeValues.CODE,
            Uri.parse(AuthenticationHelper.URL_AUTH_REDIRECT)
        )
            .setCodeVerifier(
                codeVerifier,
                codeChallenge,
                AuthenticationHelper.CODE_VERIFIER_CHALLENGE_METHOD
            )

        builder.setScopes(
            AuthenticationHelper.SCOPE_PROFILE,
            AuthenticationHelper.SCOPE_EMAIL,
            AuthenticationHelper.SCOPE_OPENID,
            AuthenticationHelper.SCOPE_TASKS,
        )

        val request = builder.build()

        return authorizationService.getAuthorizationRequestIntent(request)

    }

    fun handleAuthorizationResponse(intent: Intent) {

        val authorizationResponse: AuthorizationResponse? = AuthorizationResponse.fromIntent(intent)
        val error = AuthorizationException.fromIntent(intent)

        authState = AuthState(authorizationResponse, error)

        val tokenExchangeRequest = authorizationResponse?.createTokenExchangeRequest()

        if (tokenExchangeRequest != null) {
            authorizationService.performTokenRequest(tokenExchangeRequest) { response, exception ->
                if (exception != null) {
                    authState = AuthState()
                } else {
                    if (response != null) {
                        authState.update(response, exception)

                        jwt = JWT(response.idToken!!)


                    }
                }
                persistState()
            }
        }
    }

    private fun makeApiCall() {
        authState.performActionWithFreshTokens(authorizationService,
            object : AuthState.AuthStateAction {
                override fun execute(
                    accessToken: String?,
                    idToken: String?,
                    ex: AuthorizationException?
                ) {
                    GlobalScope.launch {
                        async(Dispatchers.IO) {
                            val client = OkHttpClient()
                            val request = Request.Builder()
                                .url(AuthenticationHelper.URL_API_CALL)
                                .addHeader("Authorization", "Bearer $accessToken")
                                .build()

                            Log.d("AccessToken", "${authState.accessToken}")
                            try {
                                val response = client.newCall(request).execute()
                                Log.d("RequestLog", "execute: ${response.code}")
                                val jsonBody = response.body?.string() ?: ""
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(
                                        context,
                                        "${response.code}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                Log.i("RequestLog", JSONObject(jsonBody).toString())
                            } catch (e: Exception) {

                                Log.d("RequestLog", "execute: Error")
                            }
                        }
                    }
                }
            }
        )
    }


}