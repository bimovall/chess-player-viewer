package org.example.chess_player_viewer.utils


sealed interface Result<out T> {
    data class Success<T>(val data: T): Result<T>
    data class Error(val cause: ErrorHandler) : Result<Nothing>
}

inline fun <T, R> Result<T>.mapSuccess(transform: (T) -> R): Result<R> {
    println("CHeck this : $this")
    return when (this) {
        is Result.Success -> try {
            Result.Success(transform(data))
        } catch (e: Exception) {
            Result.Error(ErrorHandler.UnknownError(e.message ?: ""))
        }
        is Result.Error -> this
    }
}