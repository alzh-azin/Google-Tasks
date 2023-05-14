package com.example.tasks.authentication

class AuthenticationHelper {

    companion object{

        val SHARED_PREFERENCES_NAME = "AUTH_STATE_PREFERENCE"
        val AUTH_STATE = "AUTH_STATE"

        val SCOPE_PROFILE = "profile"
        val SCOPE_EMAIL = "email"
        val SCOPE_OPENID = "openid"
        val SCOPE_TASKS = "https://www.googleapis.com/auth/tasks"
        val SCOPE_DRIVE = "https://www.googleapis.com/auth/drive"

        val DATA_PICTURE = "picture"
        val DATA_FIRST_NAME = "given_name"
        val DATA_LAST_NAME = "family_name"
        val DATA_EMAIL = "email"

        val CLIENT_ID = "1078785725973-o5ptgafakcfrih4f14n87a15a10ipv6h.apps.googleusercontent.com"
        val CODE_VERIFIER_CHALLENGE_METHOD = "S256"
        val MESSAGE_DIGEST_ALGORITHM = "SHA-256"

        val URL_AUTHORIZATION = "https://accounts.google.com/o/oauth2/v2/auth"
        val URL_TOKEN_EXCHANGE = "https://www.googleapis.com/oauth2/v4/token"
        val URL_AUTH_REDIRECT = "com.example.tasks:/oauth2redirect"
        val URL_API_CALL = "https://www.googleapis.com/drive/v3/files"
        val URL_LOGOUT = "https://accounts.google.com/o/oauth2/revoke?token="

    }
}