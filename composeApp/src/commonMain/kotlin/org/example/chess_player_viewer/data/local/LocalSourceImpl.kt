package org.example.chess_player_viewer.data.local

import kotlinx.serialization.json.Json
import org.example.chess_player_viewer.ChessPlayerDB
import org.example.chess_player_viewer.data.local.entity.ProfileEntity
import org.example.chess_player_viewer.data.local.entity.StreamingPlatformEntity

class LocalSourceImpl(private val database: ChessPlayerDB) : LocalSource {
    override fun getAllRecentlyViewedProfiles(): List<ProfileEntity> {
        return database.recentlyViewedProfilesQueries.selectLatest15().executeAsList().map {
            val platform: List<StreamingPlatformEntity> =
                if (it.streamingPlatformsJson?.isNotBlank() == true) Json.decodeFromString(it.streamingPlatformsJson) else listOf()
            ProfileEntity(
                playerId = it.playerId,
                avatar = it.avatar,
                name = it.name,
                username = it.username,
                title = it.title,
                followers = it.followers,
                country = it.country,
                location = it.location,
                lastOnline = it.lastOnline,
                joined = it.joined,
                isStreamer = it.isStreamer,
                league = it.league,
                streamingPlatforms = platform,
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
        lastOnline: Long,
        joined: Long,
        location: String,
        league: String,
        isStreamer: Boolean,
        streamingPlatformsJson: String,
        lastViewedTimestamp: Long,
    ) {
        database.recentlyViewedProfilesQueries.insertOrReplace(
            playerId = playerId,
            username = username,
            name = name,
            title = title,
            avatar = avatar,
            followers = followers,
            country = country,
            lastOnline = lastOnline,
            joined = joined,
            location = location,
            league = league,
            isStreamer = isStreamer,
            streamingPlatformsJson = streamingPlatformsJson,
            lastViewedTimestamp = lastViewedTimestamp
        )
    }
}