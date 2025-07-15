package org.example.chess_player_viewer.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.chess_player_viewer.domain.usecase.GetRecentlyViewedProfilesUseCase
import org.example.chess_player_viewer.utils.Result

class HomeViewModel(private val useCase: GetRecentlyViewedProfilesUseCase) : ViewModel() {

    private var _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getRecentlyViewed() {
        viewModelScope.launch {
            if (_uiState.value !is HomeUiState.Success) {
                _uiState.update { HomeUiState.Loading }
            }

            useCase().collect { result ->
                _uiState.update { state ->
                    when (result) {
                        is Result.Error -> HomeUiState.Error(result.cause)
                        is Result.Success -> {
                            HomeUiState.Success(result.data)
                        }
                    }
                }
            }
        }
    }

}