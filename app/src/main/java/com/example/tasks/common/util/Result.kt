package com.example.tasks.common.util

sealed class Result<out T> {

    data class Success<out T>(val data: T, val exception: Exceptions? = null) : Result<T>()

    data class Error<out T>(val exception: Exceptions) : Result<T>()

    data class Loading<out T>(val isLoading: Boolean) : Result<T>()
}
