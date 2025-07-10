package org.example.chess_player_viewer.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.chess_player_viewer.data.remote.RemoteSource
import org.example.chess_player_viewer.domain.model.PlayerStats
import org.example.chess_player_viewer.domain.model.Profile
import org.example.chess_player_viewer.domain.model.mapToPlayerStats
import org.example.chess_player_viewer.domain.model.mapToProfile
import org.example.chess_player_viewer.domain.repository.ProfileRepository
import org.example.chess_player_viewer.utils.Result
import org.example.chess_player_viewer.utils.mapSuccess

class ProfileRepositoryImpl(private val remoteSource: RemoteSource) : ProfileRepository {
    override fun getProfile(username: String): Flow<Result<Profile>> {
        return flow {
            emit(remoteSource.getProfile(username).mapSuccess { dto ->
                dto.mapToProfile()
            })
        }
    }

    override fun getPlayerStats(username: String): Flow<Result<PlayerStats>> {
        return flow {
            emit(remoteSource.getPlayerStats(username).mapSuccess { dto ->
                dto.mapToPlayerStats()
            })
        }
    }
}