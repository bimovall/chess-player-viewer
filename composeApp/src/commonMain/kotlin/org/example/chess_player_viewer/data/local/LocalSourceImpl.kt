package org.example.chess_player_viewer.data.local

import org.example.chess_player_viewer.ChessPlayerDB
import org.example.chess_player_viewer.data.local.entity.ProfileEntity

class LocalSourceImpl(private val database: ChessPlayerDB) : LocalSource {
    override fun getAllRecentlyViewedPlayer(): List<ProfileEntity> {
        return database.recentlyViewedProfilesQueries.selectLatest15().executeAsList().map {
            ProfileEntity(
                avatar = it.avatar
            )
        }
    }

    override fun insertRecentlyViewedPlayer(
        playerId: Long,
        username: String,
        name: String,
        title: String,
        avatar: String,
        followers: Long,
        country: String,
        lastOnline: String,
        joined: String,
        location: String,
        league: String,
        isStreamer: Boolean,
        streamingPlatformsJson: String,
        lastViewedTimestamp: String,
    ) {
        database.recentlyViewedProfilesQueries.insertOrReplace(
            playerId = playerId,
            username = username,
            name = name,
            title = title,
            avatar = avatar,
            followers = followers,
            country = country,
            lastOnline = followers,
            joined = followers,
            location = location,
            league = league,
            isStreamer = isStreamer,
            streamingPlatformsJson = "",
            lastViewedTimestamp = followers
        )
    }
}