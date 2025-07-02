package org.example.chess_player_viewer.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Leaderboard

@Serializable
data class DetailProfile(val username: String)