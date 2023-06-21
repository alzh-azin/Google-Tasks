package com.example.tasks.common.util

sealed class Exceptions {

    data class IOException(val message: String, val cause: Throwable) :
        Exceptions()

    object NetworkConnectionException : Exceptions()

    data class RemoteDataSourceException(val message: String? = null, val code: String?) :
        Exceptions()
}