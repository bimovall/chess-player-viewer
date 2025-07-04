package org.example.chess_player_viewer.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.chess_player_viewer.data.remote.RemoteSource
import org.example.chess_player_viewer.domain.model.Leaderboards
import org.example.chess_player_viewer.domain.model.mapToLeaderboards
import org.example.chess_player_viewer.domain.repository.LeaderboardRepository
import org.example.chess_player_viewer.utils.Result
import org.example.chess_player_viewer.utils.mapSuccess

class LeaderboardRepositoryImpl(private val remoteSource: RemoteSource) : LeaderboardRepository {
    override fun getLeaderboard(): Flow<Result<Leaderboards>> {
        return flow {
            emit(
                remoteSource.getLeaderboard().mapSuccess { dto ->
                    mapToLeaderboards(dto)
                }
            )
        }
    }

}