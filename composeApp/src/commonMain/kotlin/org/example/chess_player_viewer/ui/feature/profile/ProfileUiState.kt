package org.example.chess_player_viewer.ui.feature.profile

import org.example.chess_player_viewer.domain.model.PlayerStats
import org.example.chess_player_viewer.domain.model.Profile
import org.example.chess_player_viewer.utils.ErrorHandler

sealed class ProfileUiState {
    data object Loading : ProfileUiState()
    data class Success(val profile: Profile, val stats: PlayerStats?) : ProfileUiState()
    data class ProfileError(val error: ErrorHandler) : ProfileUiState()
    data class PlayerStatsError(val error: ErrorHandler) : ProfileUiState()
}