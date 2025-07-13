package org.example.chess_player_viewer.utils

enum class StreamType(type: String) {
    YOUTUBE("youtube"),
    TWITCH("twitch"),
    UNKNOWN("unknown");

    companion object {
        fun fromString(typeString: String?): StreamType {

            return entries.find { it.name.equals(typeString, ignoreCase = true) } ?: UNKNOWN
        }
    }

}
