package org.example.chess_player_viewer.data.remote

import io.ktor.client.HttpClient
import org.example.chess_player_viewer.data.remote.dto.PlayerDto
import org.example.chess_player_viewer.data.remote.dto.PlayerStatsDto
import org.example.chess_player_viewer.data.remote.dto.ProfileDto
import org.example.chess_player_viewer.utils.Result
import org.example.chess_player_viewer.utils.safeRequest

class RemoteSourceImpl(private val client: HttpClient) : RemoteSource {

    override suspend fun getLeaderboard(): Result<Map<String, List<PlayerDto>>> {
        return client.safeRequest("https://api.chess.com/pub/leaderboards")
    }

    override suspend fun getProfile(username: String): Result<ProfileDto> {
        return client.safeRequest("https://api.chess.com/pub/player/$username")
    }

    override suspend fun getPlayerStats(username: String): Result<PlayerStatsDto> {
        return client.safeRequest("https://api.chess.com/pub/player/$username/stats")
    }
}