package org.example.chess_player_viewer.ui.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.chess_player_viewer.domain.usecase.DeleteFavoritePlayerUseCase
import org.example.chess_player_viewer.domain.usecase.GetFavoritePlayerByPlayerIdUseCase
import org.example.chess_player_viewer.domain.usecase.GetProfilePlayerStatsUseCase
import org.example.chess_player_viewer.domain.usecase.InsertFavoritePlayer
import org.example.chess_player_viewer.utils.ErrorHandler
import org.example.chess_player_viewer.utils.Result

class ProfileViewModel(
    private val getProfilePlayerStatsUseCase: GetProfilePlayerStatsUseCase,
    private val insertFavoritePlayer: InsertFavoritePlayer,
    private val getFavoritePlayerByPlayerIdUseCase: GetFavoritePlayerByPlayerIdUseCase,
    private val deleteFavoritePlayerUseCase: DeleteFavoritePlayerUseCase
) :
    ViewModel() {

    private var _uiState: MutableStateFlow<ProfileUiState> =
        MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    fun getProfile(username: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(profileState = ProfileStatusState.Loading)
            }

            getProfilePlayerStatsUseCase.invoke(username).collect { combined ->
                val profileResult = combined.profile
                val statsResult = combined.playerStats
                _uiState.update {
                    it.copy(
                        profileState = when {
                            profileResult is Result.Success && statsResult is Result.Success -> {
                                ProfileStatusState.Success(
                                    profile = profileResult.data,
                                    stats = statsResult.data
                                )
                            }

                            profileResult is Result.Success -> {
                                ProfileStatusState.Success(
                                    profile = profileResult.data,
                                    stats = null
                                )
                            }

                            profileResult is Result.Error -> {
                                ProfileStatusState.ProfileError(profileResult.cause)
                            }

                            statsResult is Result.Error -> {
                                ProfileStatusState.PlayerStatsError(statsResult.cause)
                            }

                            else -> {
                                ProfileStatusState.Loading
                            }
                        }
                    )

                }
            }
        }
    }

    fun isFavoritePlayer(username: String) {
        viewModelScope.launch {
            getFavoritePlayerByPlayerIdUseCase(username).collect { result ->
                _uiState.update {state ->
                    state.copy(
                        favoriteState = when(result) {
                            is Result.Error -> {
                                FavoriteState.Error(result.cause)
                            }
                            is Result.Success -> {
                                if (result.data != null) {
                                    FavoriteState.Success(true)
                                } else {
                                    FavoriteState.Success(false)
                                }

                            }
                        }
                    )
                }

            }
        }
    }

    fun addFavorite() {
        viewModelScope.launch {
            if ((_uiState.value.profileState !is ProfileStatusState.Success)) {
                return@launch
            }
            val profile = (_uiState.value.profileState as ProfileStatusState.Success).profile

            insertFavoritePlayer(
                playerId = profile.playerId,
                username = profile.username,
                name = profile.name,
                title = profile.title,
                avatar = profile.avatar
            ).collect { result ->
                _uiState.update {
                    it.copy(
                        favoriteState = when(result) {
                            is Result.Error -> FavoriteState.Error(result.cause)
                            is Result.Success -> FavoriteState.Success(true)
                        }
                    )
                }
            }
        }
    }

    fun removeFavorite() {
        viewModelScope.launch {
            if ((_uiState.value.profileState !is ProfileStatusState.Success)) {
                return@launch
            }
            val profile = (_uiState.value.profileState as ProfileStatusState.Success).profile

            deleteFavoritePlayerUseCase(
                username = profile.username,
            ).collect { result ->
                _uiState.update {
                    it.copy(
                        favoriteState = when(result) {
                            is Result.Error -> FavoriteState.Error(result.cause)
                            is Result.Success -> FavoriteState.Success(false)
                        }
                    )
                }
            }
        }
    }
}