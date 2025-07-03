package org.example.chess_player_viewer.data.remote

import io.ktor.client.HttpClient

class RemoteSourceImpl(httpClient: HttpClient) : RemoteSource {
    override suspend fun getLeaderboard() {
        println("Check calling get leaderboard")
    }
}