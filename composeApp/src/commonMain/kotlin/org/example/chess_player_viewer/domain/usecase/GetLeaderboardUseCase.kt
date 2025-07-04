package org.example.chess_player_viewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.example.chess_player_viewer.domain.model.Leaderboards
import org.example.chess_player_viewer.domain.repository.LeaderboardRepository
import org.example.chess_player_viewer.utils.Result

class GetLeaderboardUseCase(private val repository: LeaderboardRepository) {

    operator fun invoke(): Flow<Result<Leaderboards>> {
        return repository.getLeaderboard()
    }
}