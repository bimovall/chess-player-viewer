package org.example.chess_player_viewer.data.local

import org.example.chess_player_viewer.data.local.entity.ProfileEntity

interface LocalSource {

    fun getAllRecentlyViewedProfiles(): List<ProfileEntity>

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
}