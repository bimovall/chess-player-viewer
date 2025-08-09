package org.example.chess_player_viewer.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.chess_player_viewer.data.local.LocalSource
import org.example.chess_player_viewer.data.remote.RemoteSource
import org.example.chess_player_viewer.domain.model.Leaderboards
import org.example.chess_player_viewer.domain.model.mapToLeaderboard
import org.example.chess_player_viewer.domain.model.mapToLeaderboardEntity
import org.example.chess_player_viewer.domain.model.mapToLeaderboards
import org.example.chess_player_viewer.domain.repository.LeaderboardRepository
import org.example.chess_player_viewer.utils.Result
import org.example.chess_player_viewer.utils.mapSuccess

class LeaderboardRepositoryImpl(private val remoteSource: RemoteSource, private val localSource: LocalSource) : LeaderboardRepository {
    override fun getLeaderboard(): Flow<Result<Leaderboards>> {
        return flow {

            val localData = localSource.getLeaderboard()

            if (localData is Result.Success) {
                if (localData.data.isNotEmpty()) {
                    emit(localData.mapSuccess {
                        mapToLeaderboard(it)
                    })
                }
            }

            emit(
                remoteSource.getLeaderboard().mapSuccess { dto ->
                    localSource.insertLeaderboard(mapToLeaderboardEntity(dto))
                    mapToLeaderboards(dto)
                }
            )

            when (val remoteResult = remoteSource.getLeaderboard()) {
                is Result.Success -> {
                    val leaderboards = mapToLeaderboards(remoteResult.data)

                    // Emit data immediately
                    emit(Result.Success(leaderboards))

                    // Insert in background without blocking
                    localSource.insertLeaderboard(mapToLeaderboardEntity(remoteResult.data))
                }
                is Result.Error -> {
                    emit(remoteResult)
                }
            }
        }
    }

}