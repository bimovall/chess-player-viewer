package org.example.chess_player_viewer.ui.feature.home

import org.example.chess_player_viewer.domain.model.Profile
import org.example.chess_player_viewer.utils.ErrorHandler

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(val profiles: List<Profile>) : HomeUiState()
    data class Error(val error: ErrorHandler) : HomeUiState()
}