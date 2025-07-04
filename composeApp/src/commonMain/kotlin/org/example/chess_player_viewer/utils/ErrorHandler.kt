package org.example.chess_player_viewer.utils

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException

sealed class ErrorHandler(message: String): Throwable(message) {
    data class NetworkConnection(override val message: String) : ErrorHandler(message)
    data class NotFound(override val message: String) : ErrorHandler(message)
    data class RateLimit(override val message: String) : ErrorHandler(message)
    data class ServerError(override val message: String) : ErrorHandler(message)
    data class UnknownError(override val message: String) : ErrorHandler(message)

}

suspend inline fun <reified T> HttpResponse.responseHandler(): Result<T> {
    return try {
        val message = this.status.description
        when (this.status.value) {
            in 200..299 -> {
                Result.Success(this.body<T>())
            }
            404 -> {
                Result.Error(ErrorHandler.NotFound(message))
            }
            410 -> {
                Result.Error(ErrorHandler.NotFound(message))
            }
            429 -> {
                Result.Error(ErrorHandler.RateLimit(message))
            }
            else -> {
                Result.Error(ErrorHandler.ServerError(message))
            }

        }

    } catch (e: UnresolvedAddressException) {
        Result.Error(ErrorHandler.NetworkConnection(e.message.orEmpty()))
    } catch (e: Exception) {
        Result.Error(ErrorHandler.UnknownError(e.message.orEmpty()))
    }

}
