package org.example.chess_player_viewer.ui.feature.favorite_player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.chess_player_viewer.domain.usecase.GetAllFavoritePlayerUseCase
import org.example.chess_player_viewer.utils.Result

class FavoritePlayerViewModel(private val getAllFavoritePlayerUseCase: GetAllFavoritePlayerUseCase) :
    ViewModel() {

    private var _uiState = MutableStateFlow<FavoritePlayerUiState>(FavoritePlayerUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getAllFavoritePlayer() {
        viewModelScope.launch {
            getAllFavoritePlayerUseCase().collect { result ->
                _uiState.update { state ->
                    when (result) {
                        is Result.Error -> FavoritePlayerUiState.Error(result.cause)
                        is Result.Success -> FavoritePlayerUiState.Success(result.data)
                    }
                }
            }
        }
    }
}