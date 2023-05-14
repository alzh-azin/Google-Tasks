package com.example.tasks.common.data.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TasksPreferences @Inject constructor(
    @ApplicationContext context : Context
): Preferences{
    override fun getToken(): String {
        return ""
    }

    override fun getAuthState(): String {
        return ""
    }

    override fun putToken(token: String) {

    }

    override fun putAuthState(authState: String) {
    }

    override fun deleteToken() {
    }
}