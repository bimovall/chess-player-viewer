package org.example.chess_player_viewer.domain.model

import org.example.chess_player_viewer.data.local.entity.FavoritePlayerEntity

data class FavoritePlayer(
    val playerId: Long,
    val username: String,
    val name: String,
    val title: String,
    val avatar: String
)

fun FavoritePlayerEntity.mapToFavoritePlayer() = FavoritePlayer(
    playerId = this.playerId,
    username = this.username,
    name = this.name,
    title = this.title,
    avatar = this.avatar
)