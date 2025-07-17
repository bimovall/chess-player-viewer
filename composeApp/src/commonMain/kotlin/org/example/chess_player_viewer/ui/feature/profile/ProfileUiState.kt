package org.example.chess_player_viewer.ui.feature.profile

import org.example.chess_player_viewer.domain.model.FavoritePlayer
import org.example.chess_player_viewer.domain.model.PlayerStats
import org.example.chess_player_viewer.domain.model.Profile
import org.example.chess_player_viewer.utils.ErrorHandler

data class ProfileUiState(
    val profileState: ProfileStatusState = ProfileStatusState.Loading,
    val favoriteState: FavoriteState = FavoriteState.Loading,
)

sealed class ProfileStatusState {
    data object Loading : ProfileStatusState()
    data class Success(val profile: Profile, val stats: PlayerStats?) : ProfileStatusState()
    data class ProfileError(val error: ErrorHandler) : ProfileStatusState()
    data class PlayerStatsError(val error: ErrorHandler) : ProfileStatusState()
}

sealed class FavoriteState {
    data object Loading : FavoriteState()
    data class Success(val isDataAvailable: Boolean) : FavoriteState()
    data class Error(val error: ErrorHandler) : FavoriteState()
}