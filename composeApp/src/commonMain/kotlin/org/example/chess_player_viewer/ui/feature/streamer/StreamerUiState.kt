package org.example.chess_player_viewer.ui.feature.streamer

import org.example.chess_player_viewer.domain.model.Streamer
import org.example.chess_player_viewer.utils.ErrorHandler

sealed class StreamerUiState {
    data object Loading : StreamerUiState()
    data class Success(val data: List<Streamer>) : StreamerUiState()
    data class Error(val error: ErrorHandler) : StreamerUiState()
}