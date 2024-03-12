/*
 * Copyright (c) 2023 Lanit-Tercom, LLC.
 */

package com.example.vktestapp.core.network.extensions

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException

/**
 * Checks the HTTP response code and handles different status codes accordingly
 *
 * @return a [Result] with the response data of type [T]
 */
internal suspend inline fun <reified T> HttpResponse.handleResponse() = when {
    status.isSuccess() -> {
        Log.d("Response", "Response: $this")
        Result.success(value = body<T>())
    }

    status.isRedirection() -> {
        Result.failure<T>(
            exception = ResponseExceptionWithMessage(
                responseException = RedirectResponseException(this, "Redirection error"),
                serverMessage = parseErrors()
            )
        ).also { result ->
            Log.e("Response", "Redirection error: ${result.exceptionOrNull()}")
        }
    }

    status.isClientError() -> {
        Result.failure<T>(
            exception = ResponseExceptionWithMessage(
                responseException = ClientRequestException(this, "Client error"),
                serverMessage = parseErrors()
            )
        ).also { result ->
            Log.e("Response", "Client error: ${result.exceptionOrNull()}")
        }
    }

    status.isServerError() -> {
        Result.failure<T>(
            exception = ResponseExceptionWithMessage(
                responseException = ServerResponseException(this, "Server error"),
                serverMessage = parseErrors()
            )
        ).also { result ->
            Log.e("Response", "Server error: ${result.exceptionOrNull()}")
        }
    }

    else -> {
        Log.e("Response", "Unknown response status code: $this")
        Result.failure(exception = ResponseException(this, status.description))
    }
}

/**
 * Performs a request and checks the response code for error handling
 *
 * @param [block] the lambda for configuring the HTTP request
 * @return a [Result] with the response data of type [T]
 */
internal suspend inline fun <reified T> HttpClient.request(block: HttpClient.() -> HttpResponse): Result<T> {
    return try {
        block().handleResponse()
    } catch (exception: Exception) {
        Log.e("Response", "Unknown error: $exception")
        Result.failure(exception = exception)
    }
}

/**
 * Parse request response errors
 */
internal suspend inline fun HttpResponse.parseErrors(): String {
    return try {
        body<ErrorResponse>().errors.firstOrNull() ?: "Что то пошло не так"
    } catch (exception: Exception) {
        when (exception) {
            is SerializationException -> "Ошибка сериализации"
            is IllegalArgumentException -> "Невозможно предствавить как ErrorResponse"
            else -> "Что то пошло не так"
        }
    }
}

/**
 * A class that contains info about errors of response
 *
 * @param [errors] contains list of errors
 */
@Serializable
data class ErrorResponse(
    val errors: List<String>
)

/**
 * Error for storing message from server
 *
 * @property [responseException] request exception
 * @property [serverMessage] message from server
 */
class ResponseExceptionWithMessage(
    val responseException: ResponseException,
    val serverMessage: String
) : Exception(responseException) {

    override val message: String
        get() = serverMessage
}
