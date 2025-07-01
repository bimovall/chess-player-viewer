package org.example.chess_player_viewer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform