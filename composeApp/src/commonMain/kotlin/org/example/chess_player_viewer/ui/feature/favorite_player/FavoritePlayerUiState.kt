package org.example.chess_player_viewer.ui.feature.favorite_player

import org.example.chess_player_viewer.domain.model.FavoritePlayer
import org.example.chess_player_viewer.utils.ErrorHandler

sealed class FavoritePlayerUiState {
    data object Loading : FavoritePlayerUiState()
    data class Success(val data: List<FavoritePlayer>) : FavoritePlayerUiState()
    data class Error(val errorHandler: ErrorHandler) : FavoritePlayerUiState()
}