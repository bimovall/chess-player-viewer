package org.example.chess_player_viewer.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDto(
    @SerialName("player_id") val playerId: Long,
    @SerialName("@id") val id: String,
    val url: String,
    val username: String,
    val score: Long,
    val rank: Long,
    val title: String? = null,
    val name: String? = null,
    val avatar: String
)