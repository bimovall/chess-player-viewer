package org.example.chess_player_viewer.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.example.chess_player_viewer.data.remote.dto.PlayerDto
import org.example.chess_player_viewer.utils.Result
import org.example.chess_player_viewer.utils.responseHandler

class RemoteSourceImpl(private val client: HttpClient) : RemoteSource {

    override suspend fun getLeaderboard(): Result<Map<String, List<PlayerDto>>> {
        return client.get("https://api.chess.com/pub/leaderboards")
            .responseHandler<Map<String, List<PlayerDto>>>()
    }
}