package com.example.google_tasks.authentication.data

import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import javax.inject.Inject

class AuthenticationService @Inject constructor(
    private val authState: AuthState,
    private val authorizationService: AuthorizationService,
    private val authServiceConfig: AuthorizationServiceConfiguration
) {

}