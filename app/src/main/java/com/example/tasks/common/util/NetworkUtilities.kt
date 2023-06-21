package com.example.tasks.common.util

import retrofit2.Response
import java.net.SocketTimeoutException

suspend fun <T> getResponseResult(
    call: suspend () -> Response<T>,
    requestErrorMessage: String,
): Result<T> {

    val response = try {
        call()
    } catch (_: SocketTimeoutException) {

        return Result.Error(
            Exceptions.RemoteDataSourceException(code = ApiErrorCategory.TimeoutError.value)
        )
    } catch (e: Exception) {

        return Result.Error(Exceptions.IOException(requestErrorMessage, e))
    }

    return if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
            Result.Success(body)
        } else {

            Result.Error(
                Exceptions.RemoteDataSourceException(
                    message = "Body is empty",
                    code = response.code().toString()
                )
            )
        }
    } else {

        val error =
            ApiError(message = response.errorBody()?.string(), code = response.code().toString())
        Result.Error(Exceptions.RemoteDataSourceException(error.code, error.message))
    }


}