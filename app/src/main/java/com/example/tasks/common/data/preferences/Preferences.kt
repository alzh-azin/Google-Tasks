package com.example.tasks.common.data.preferences

interface Preferences {
    fun getToken(): String

    fun getAuthState(): String

    fun putToken(token: String)

    fun putAuthState(authState: String)

    fun deleteToken()
}