package org.example.chess_player_viewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import org.example.chess_player_viewer.domain.model.ProfileWithPlayerStats
import org.example.chess_player_viewer.domain.repository.ProfileRepository

class GetProfilePlayerStatsUseCase(private val repository: ProfileRepository) {

    operator fun invoke(username: String): Flow<ProfileWithPlayerStats> {
        return repository.getProfile(username)
            .zip(repository.getPlayerStats(username)) { profileResult, statsResult ->
                ProfileWithPlayerStats(
                    profile = profileResult,
                    playerStats = statsResult
                )
            }
    }
}