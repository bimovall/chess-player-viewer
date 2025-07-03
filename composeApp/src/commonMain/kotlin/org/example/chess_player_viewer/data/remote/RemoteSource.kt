package org.example.chess_player_viewer.data.remote

interface RemoteSource {

    suspend fun getLeaderboard()
}