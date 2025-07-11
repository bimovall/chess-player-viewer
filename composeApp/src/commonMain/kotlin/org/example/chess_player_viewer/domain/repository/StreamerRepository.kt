package org.example.chess_player_viewer.domain.repository

import kotlinx.coroutines.flow.Flow
import org.example.chess_player_viewer.domain.model.Streamer
import org.example.chess_player_viewer.utils.Result

interface StreamerRepository {
    fun getStreamers(): Flow<Result<List<Streamer>>>
}