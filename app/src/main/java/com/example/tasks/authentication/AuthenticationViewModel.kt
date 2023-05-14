package com.example.tasks.authentication

import android.content.Intent
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val authenticationConfig: AuthenticationConfig) :
    ViewModel() {

    fun attemptAuthentication() = authenticationConfig.attemptAuthorization()

    fun handleAuthorizationResponse(intent: Intent) =
        authenticationConfig.handleAuthorizationResponse(intent)

}