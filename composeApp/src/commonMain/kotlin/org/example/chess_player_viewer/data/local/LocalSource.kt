package org.example.chess_player_viewer.data.local

import org.example.chess_player_viewer.data.local.entity.FavoritePlayerEntity
import org.example.chess_player_viewer.data.local.entity.ProfileEntity
import org.example.chess_player_viewer.domain.model.FavoritePlayer
import org.example.chess_player_viewer.utils.Result

interface LocalSource {

    fun getAllRecentlyViewedProfiles(): Result<List<ProfileEntity>>

    fun insertRecentlyViewedPlayer(
        playerId: Long,
        username: String,
        name: String,
        title: String,
        avatar: String,
        followers: Long,
        country: String,
        lastOnline: Long,
        joined: Long,
        location: String,
        league: String,
        isStreamer: Boolean,
        streamingPlatformsJson: String,
        lastViewedTimestamp: Long,
    )

    fun getAllFavoritePlayer(): Result<List<FavoritePlayerEntity>>

    fun getFavoritePlayerByUsername(username: String): Result<FavoritePlayerEntity?>

    fun insertFavoritePlayer(player: FavoritePlayerEntity): Result<Long>

    fun deleteFavoritePlayer(username: String): Result<Boolean>
}