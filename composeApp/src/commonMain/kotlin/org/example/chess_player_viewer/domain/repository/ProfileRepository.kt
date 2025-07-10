package org.example.chess_player_viewer.domain.repository

import kotlinx.coroutines.flow.Flow
import org.example.chess_player_viewer.domain.model.PlayerStats
import org.example.chess_player_viewer.domain.model.Profile
import org.example.chess_player_viewer.utils.Result

interface ProfileRepository {
    fun getProfile(username: String): Flow<Result<Profile>>

    fun getPlayerStats(username: String): Flow<Result<PlayerStats>>
}