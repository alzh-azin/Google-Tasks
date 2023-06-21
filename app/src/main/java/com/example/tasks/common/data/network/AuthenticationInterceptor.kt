package com.example.tasks.common.data.network

import android.content.Context
import com.example.tasks.common.data.network.ApiHelper.AUTH_HEADER
import com.example.tasks.common.data.network.ApiHelper.TOKEN_TYPE
import com.example.tasks.common.data.proto.TasksPreferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import net.openid.appauth.AppAuthConfiguration
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationService
import net.openid.appauth.browser.BrowserAllowList
import net.openid.appauth.browser.VersionedBrowserMatcher
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val dataStore: TasksPreferencesDataStore,
    @ApplicationContext private val context: Context,
) : Interceptor {

    private lateinit var authorizationService: AuthorizationService


    init {
        initAuthService()
    }

    override fun intercept(chain: Interceptor.Chain): Response {


        return chain.createAuthenticatedResponse(refreshToken())
    }


    private fun Interceptor.Chain.createAuthenticatedResponse(token: String): Response {
        val request = request()
            .newBuilder()
            .addHeader(AUTH_HEADER, TOKEN_TYPE + token)
            .build()
        request.url.queryParameter(ApiHelper.KEY)
        return proceed(request)

    }

    private fun refreshToken(): String {

        var token = ""

        runBlocking {

            val authStateDeferred = CoroutineScope(Dispatchers.IO).async {
                AuthState.jsonDeserialize(dataStore.getAuthState().orEmpty())
            }

            val authState = authStateDeferred.await()

            authState.performActionWithFreshTokens(
                authorizationService
            ) { accessToken, _, _ ->
                token = accessToken.orEmpty()
            }

            dataStore.setAuthState(authState.jsonSerializeString())
        }

        return token

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

    fun onDestroy() {

    }

    companion object {
        const val UNAUTHORIZED = 401
    }


}