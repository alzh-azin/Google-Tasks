package com.example.google_tasks.authentication.di

import android.net.Uri
import com.example.google_tasks.GoogleTasksApplication
import com.example.google_tasks.authentication.data.AuthenticationHelper
import com.example.google_tasks.common.data.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.openid.appauth.AppAuthConfiguration
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.browser.BrowserAllowList
import net.openid.appauth.browser.VersionedBrowserMatcher

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    fun provideAuthState(preferences: Preferences): AuthState {
        val authStateJsonString = preferences.getAuthState()
        return AuthState.jsonDeserialize(authStateJsonString)
    }

    @Provides
    fun provideAppAuthConfiguration(): AppAuthConfiguration {
        return AppAuthConfiguration.Builder()
            .setBrowserMatcher(
                BrowserAllowList(
                    VersionedBrowserMatcher.CHROME_CUSTOM_TAB,
                    VersionedBrowserMatcher.SAMSUNG_CUSTOM_TAB
                )
            ).build()

    }

    @Provides
    fun provideAuthorizationService(
        application: GoogleTasksApplication,
        appAuthConfiguration: AppAuthConfiguration
    ): AuthorizationService {

        return AuthorizationService(
            application,
            appAuthConfiguration
        )
    }

    @Provides
    fun provideAuthorizationServiceConfiguration(): AuthorizationServiceConfiguration {
        return AuthorizationServiceConfiguration(
            Uri.parse(AuthenticationHelper.URL_AUTHORIZATION),
            Uri.parse(AuthenticationHelper.URL_TOKEN_EXCHANGE),
            null,
            Uri.parse(AuthenticationHelper.URL_LOGOUT)
        )

    }
}