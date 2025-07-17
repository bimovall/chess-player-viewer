package org.example.chess_player_viewer.domain.repository

import kotlinx.coroutines.flow.Flow
import org.example.chess_player_viewer.data.local.entity.FavoritePlayerEntity
import org.example.chess_player_viewer.domain.model.FavoritePlayer
import org.example.chess_player_viewer.domain.model.PlayerStats
import org.example.chess_player_viewer.domain.model.Profile
import org.example.chess_player_viewer.utils.Result

interface ProfileRepository {
    fun getProfile(username: String): Flow<Result<Profile>>

    fun getPlayerStats(username: String): Flow<Result<PlayerStats>>

    fun getRecentlyViewedProfiles(count: Int): Flow<Result<List<Profile>>>

    fun getAllFavoritePlayers(): Flow<Result<List<FavoritePlayer>>>

    fun getFavoritePlayerByPlayerId(username: String): Flow<Result<FavoritePlayer?>>

    fun insertFavoritePlayer(player: FavoritePlayerEntity): Flow<Result<Long>>

    fun deleteFavoritePlayer(username: String): Flow<Result<Boolean>>
}