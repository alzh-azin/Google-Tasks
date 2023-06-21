package com.example.tasks.common.data.proto

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.tasks.datastore.UserPreferences
import com.example.tasks.datastore.copy
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class TasksPreferencesDataStore @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {

    private val TAG: String = "UserPreferences"

    val userData = userPreferences.data.map { userPref ->
        UserData(
            authState = userPref.authState
        )
    }.catch { exception ->
        if (exception is IOException) {
            Log.e(TAG, "Error reading data", exception)
        } else {
            throw exception
        }
    }

    suspend fun setAuthState(authState: String) {
        try {
            userPreferences.updateData {
                it.copy {
                    this.authState = authState
                }
            }
        } catch (ioException: IOException) {
            Log.e(TAG, "Failed to update user preferences", ioException)
        }
    }

    suspend fun getAuthState() = userData.map {
        it.authState
    }.firstOrNull()

}