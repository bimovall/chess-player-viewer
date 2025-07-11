package org.example.chess_player_viewer.data.remote

import org.example.chess_player_viewer.data.remote.dto.PlayerDto
import org.example.chess_player_viewer.data.remote.dto.PlayerStatsDto
import org.example.chess_player_viewer.data.remote.dto.ProfileDto
import org.example.chess_player_viewer.data.remote.dto.StreamersDto
import org.example.chess_player_viewer.utils.Result

interface RemoteSource {

    suspend fun getLeaderboard(): Result<Map<String, List<PlayerDto>>>

    suspend fun getProfile(username: String): Result<ProfileDto>

    suspend fun getPlayerStats(username: String): Result<PlayerStatsDto>

    suspend fun getStreamers(): Result<StreamersDto>
}