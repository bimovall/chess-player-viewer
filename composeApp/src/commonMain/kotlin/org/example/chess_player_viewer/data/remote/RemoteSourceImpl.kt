package org.example.chess_player_viewer.data.remote

import org.example.chess_player_viewer.data.remote.dto.PlayerDto
import org.example.chess_player_viewer.data.remote.dto.PlayerStatsDto
import org.example.chess_player_viewer.data.remote.dto.ProfileDto
import org.example.chess_player_viewer.data.remote.dto.StreamersDto
import org.example.chess_player_viewer.data.service.ApiService
import org.example.chess_player_viewer.utils.Result
import org.example.chess_player_viewer.utils.safeApiCall

class RemoteSourceImpl(private val apiService: ApiService) :
    RemoteSource {

    override suspend fun getLeaderboard(): Result<Map<String, List<PlayerDto>>> {
        return safeApiCall {
            apiService.getLeaderboard()
        }
    }

    override suspend fun getProfile(username: String): Result<ProfileDto> {
        return safeApiCall {
            apiService.getProfile(username)
        }
    }

    override suspend fun getPlayerStats(username: String): Result<PlayerStatsDto> {
        return safeApiCall {
            apiService.getPlayerStats(username)
        }
    }

    override suspend fun getStreamers(): Result<StreamersDto> {
        return safeApiCall {
            apiService.getStreamers()
        }
    }
}