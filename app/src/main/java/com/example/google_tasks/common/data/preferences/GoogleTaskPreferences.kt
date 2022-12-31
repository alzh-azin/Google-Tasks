package com.example.google_tasks.common.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.google_tasks.common.data.preferences.PreferencesHelper.KEY_AUTH_STATE
import com.example.google_tasks.common.data.preferences.PreferencesHelper.KEY_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GoogleTaskPreferences @Inject constructor(
    @ApplicationContext context: Context
) : Preferences {

    companion object {
        const val PREFERENCES_NAME = "GOOGLE_TASK_PREFERENCES"
    }

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun putAuthState(authState: String) {
        edit {
            putString(KEY_AUTH_STATE, authState)
        }
    }

    override fun getAuthState(): String {
        return preferences.getString(KEY_AUTH_STATE , "").orEmpty()
    }

    override fun putToken(token: String) {
        edit {
            putString(KEY_TOKEN , token)
        }
    }

    override fun getToken(): String {
        return preferences.getString(KEY_TOKEN , "").orEmpty()
    }

    private inline fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(preferences.edit()) {
            block()
            commit()
        }
    }


}