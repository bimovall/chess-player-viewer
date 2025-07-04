package org.example.chess_player_viewer.ui.feature.leaderboard

import org.example.chess_player_viewer.domain.model.Leaderboards
import org.example.chess_player_viewer.utils.ErrorHandler

sealed class LeaderboardUiState {
    data object Loading : LeaderboardUiState()
    data class Success(val leaderboards: Leaderboards, val error: ErrorHandler? = null, val selectedFilter: String = "daily") : LeaderboardUiState()
    data class Error(val error: ErrorHandler) : LeaderboardUiState()
}