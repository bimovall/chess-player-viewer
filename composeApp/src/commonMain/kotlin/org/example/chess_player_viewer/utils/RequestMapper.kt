package org.example.chess_player_viewer.utils

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException


suspend inline fun <reified T> HttpClient.safeRequest(url: String): Result<T> {
    return try {
        val response = this.get(url)
        // Handle response using the same logic as responseHandler
        try {
            val message = response.status.description
            when (response.status.value) {
                in 200..299 -> {
                    Result.Success(response.body<T>())
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
        } catch (e: Exception) {
            Result.Error(ErrorHandler.UnknownError(e.message.orEmpty()))
        }
    } catch (e: UnresolvedAddressException) {
        Result.Error(ErrorHandler.NetworkConnection(e.message.orEmpty()))
    } catch (e: ConnectTimeoutException) {
        Result.Error(ErrorHandler.NetworkConnection(e.message.orEmpty()))
    } catch (e: Exception) {
        Result.Error(ErrorHandler.UnknownError(e.message.orEmpty()))
    }
}