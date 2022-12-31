package com.example.google_tasks.common.data.preferences

interface Preferences {

    fun putAuthState(authState : String)

    fun getAuthState() : String

    fun putToken(token : String)

    fun getToken() : String
}