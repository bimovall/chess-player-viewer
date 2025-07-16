package org.example.chess_player_viewer.data.local.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoritePlayerEntity(
    @SerialName("playerId") val playerId : Long,
    @SerialName("username") val username : String,
    @SerialName("name") val name : String,
    @SerialName("title") val title : String,
    @SerialName("avatar") val avatar : String
)