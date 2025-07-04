package org.example.chess_player_viewer.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDto(
    @SerialName("player_id") val playerId: Int,
    @SerialName("@id") val id: String,
    val url: String,
    val username: String,
    val score: Int,
    val rank: Int,
    val title: String? = null,
    val name: String? = null,
    val avatar: String
)