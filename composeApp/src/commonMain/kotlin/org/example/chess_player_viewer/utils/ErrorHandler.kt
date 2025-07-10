package org.example.chess_player_viewer.utils

sealed class ErrorHandler(message: String) : Throwable(message) {
    data class NetworkConnection(override val message: String) : ErrorHandler(message)
    data class NotFound(override val message: String) : ErrorHandler(message)
    data class RateLimit(override val message: String) : ErrorHandler(message)
    data class ServerError(override val message: String) : ErrorHandler(message)
    data class UnknownError(override val message: String) : ErrorHandler(message)

}

