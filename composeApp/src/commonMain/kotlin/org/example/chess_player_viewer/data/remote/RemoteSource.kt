package org.example.chess_player_viewer.data.remote

import org.example.chess_player_viewer.data.remote.dto.PlayerDto
import org.example.chess_player_viewer.utils.Result

interface RemoteSource {

    suspend fun getLeaderboard(): Result<Map<String, List<PlayerDto>>>
}