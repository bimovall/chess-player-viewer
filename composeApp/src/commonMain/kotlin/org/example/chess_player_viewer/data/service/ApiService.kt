package org.example.chess_player_viewer.data.service

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class ApiService(private val client: HttpClient) {
    suspend fun getLeaderboard(): HttpResponse {
        return client.get("https://api.chess.com/pub/leaderboards")
    }

    suspend fun getProfile(username: String): HttpResponse {
        return client.get("https://api.chess.com/pub/player/$username")
    }

    suspend fun getPlayerStats(username: String): HttpResponse {
        return client.get("https://api.chess.com/pub/player/$username/stats")
    }
}