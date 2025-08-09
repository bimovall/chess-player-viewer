package org.example.chess_player_viewer.data.local.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeaderboardEntity(
    @SerialName("playerId") val playerId : Long,
    @SerialName("id") val id : String,
    @SerialName("username") val username : String,
    @SerialName("name") val name : String,
    @SerialName("title") val title : String,
    @SerialName("avatar") val avatar : String,
    @SerialName("score") val score : Long,
    @SerialName("rank") val rank : Long,
    @SerialName("url") val url : String,
    @SerialName("category") val category : String,
)
