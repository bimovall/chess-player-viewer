package org.example.chess_player_viewer.ui.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.chess_player_viewer.domain.usecase.GetProfilePlayerStatsUseCase
import org.example.chess_player_viewer.utils.Result

class ProfileViewModel(private val getProfilePlayerStatsUseCase: GetProfilePlayerStatsUseCase) :
    ViewModel() {

    private var _uiState: MutableStateFlow<ProfileUiState> =
        MutableStateFlow(ProfileUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getProfile(username: String) {
        viewModelScope.launch {
            _uiState.update {
                ProfileUiState.Loading
            }

            getProfilePlayerStatsUseCase.invoke(username).collect { combined ->
                val profileResult = combined.profile
                val statsResult = combined.playerStats
                _uiState.update {
                    when {
                        profileResult is Result.Success && statsResult is Result.Success -> {
                            ProfileUiState.Success(
                                profile = profileResult.data,
                                stats = statsResult.data
                            )
                        }

                        profileResult is Result.Success -> {
                            ProfileUiState.Success(
                                profile = profileResult.data,
                                stats = null
                            )
                        }

                        profileResult is Result.Error -> {
                            ProfileUiState.ProfileError(profileResult.cause)
                        }

                        statsResult is Result.Error -> {
                            ProfileUiState.PlayerStatsError(statsResult.cause)
                        }

                        else -> {
                            ProfileUiState.Loading
                        }
                    }

                }
            }
        }
    }
}