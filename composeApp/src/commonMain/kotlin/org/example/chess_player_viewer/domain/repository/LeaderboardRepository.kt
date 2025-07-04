package org.example.chess_player_viewer.domain.repository

import kotlinx.coroutines.flow.Flow
import org.example.chess_player_viewer.domain.model.Leaderboards
import org.example.chess_player_viewer.utils.Result

interface LeaderboardRepository {

    fun getLeaderboard(): Flow<Result<Leaderboards>>
}