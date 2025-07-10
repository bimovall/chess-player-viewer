package org.example.chess_player_viewer.domain.model

import org.example.chess_player_viewer.utils.Result

data class ProfileWithPlayerStats(
    val profile: Result<Profile>,
    val playerStats: Result<PlayerStats>
)
