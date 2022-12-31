package com.example.google_tasks.authentication.data

class AuthenticationHelper {
    companion object {

        const val SCOPE_PROFILE = "profile"
        const val SCOPE_EMAIL = "email"
        const val SCOPE_OPENID = "openid"
        const val SCOPE_DRIVE = "https://www.googleapis.com/auth/drive"
        const val SCOPE_CALENDAR = "https://www.googleapis.com/auth/calendar"
        const val SCOPE_TASKS = "https://www.googleapis.com/auth/tasks"

        const val CLIENT_ID = "319538641639-f6c0pqjcsvqmue4r05kert9p8u3fnvkm.apps.googleusercontent.com"
        const val CODE_VERIFIER_CHALLENGE_METHOD = "S256"
        const val MESSAGE_DIGEST_ALGORITHM = "SHA-256"

        const val URL_AUTHORIZATION = "https://accounts.google.com/o/oauth2/v2/auth"
        const val URL_TOKEN_EXCHANGE = "https://www.googleapis.com/oauth2/v4/token"
        const val URL_AUTH_REDIRECT = "com.example.sampleoauth:/oauth2redirect"
        const val URL_LOGOUT = "https://accounts.google.com/o/oauth2/revoke?token="
    }
}