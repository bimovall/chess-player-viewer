package org.example.chess_player_viewer.ui.feature.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.chess_player_viewer.domain.usecase.GetLeaderboardUseCase
import org.example.chess_player_viewer.utils.Result

class LeaderboardViewModel(val getLeaderboardUseCase: GetLeaderboardUseCase) : ViewModel() {

    private var _uiState = MutableStateFlow<LeaderboardUiState>(LeaderboardUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        //TODO move to compose to avoid early calls in Unit Test
        getLeaderboards()
    }

    private fun getLeaderboards(filter: String = "daily") {
        viewModelScope.launch {
            getLeaderboardUseCase().collect { result ->
                _uiState.update { old ->
                    when {
                        result is Result.Success -> {
                            LeaderboardUiState.Success(result.data, selectedFilter = filter)
                        }

                        result is Result.Error && old is LeaderboardUiState.Success -> {
                            old.copy(error = result.cause)
                        }

                        result is Result.Error -> LeaderboardUiState.Error(result.cause)

                        else -> old
                    }
                }
            }
        }
    }

    fun updateFilter(filter: String) {
        if (uiState.value is LeaderboardUiState.Success) {
            _uiState.update {
                (_uiState.value as LeaderboardUiState.Success).copy(selectedFilter = filter)
            }
        }
    }
}