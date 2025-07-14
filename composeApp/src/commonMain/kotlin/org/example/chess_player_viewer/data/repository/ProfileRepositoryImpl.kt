@file:OptIn(ExperimentalTime::class)

package org.example.chess_player_viewer.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.example.chess_player_viewer.data.local.LocalSource
import org.example.chess_player_viewer.data.remote.RemoteSource
import org.example.chess_player_viewer.domain.model.PlayerStats
import org.example.chess_player_viewer.domain.model.Profile
import org.example.chess_player_viewer.domain.model.mapToPlayerStats
import org.example.chess_player_viewer.domain.model.mapToProfile
import org.example.chess_player_viewer.domain.repository.ProfileRepository
import org.example.chess_player_viewer.utils.Result
import org.example.chess_player_viewer.utils.mapSuccess
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class ProfileRepositoryImpl(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource,
    private val clock: Clock
) : ProfileRepository {
    override fun getProfile(username: String): Flow<Result<Profile>> {
        return flow {
            val result = remoteSource.getProfile(username).mapSuccess { dto ->
                dto.mapToProfile()
            }

            if (result is Result.Success) {
                val profile = result.data
                insertProfileToRecentlyViewed(profile)
            }

            emit(result)
        }
    }

    override fun getPlayerStats(username: String): Flow<Result<PlayerStats>> {
        return flow {
            emit(remoteSource.getPlayerStats(username).mapSuccess { dto ->
                dto.mapToPlayerStats()
            })
        }
    }

    private fun insertProfileToRecentlyViewed(profile: Profile) {
        localSource.insertRecentlyViewedPlayer(
            playerId = profile.playerId,
            username = profile.username,
            name = profile.name,
            title = profile.title,
            avatar = profile.avatar,
            followers = profile.followers.toLong(),
            country = profile.country,
            lastOnline = profile.lastOnline,
            joined = profile.joined,
            location = profile.location,
            league = profile.league,
            isStreamer = profile.isStreamer,
            streamingPlatformsJson = if (profile.streamingPlatforms.isNotEmpty()) Json.encodeToString(
                profile.streamingPlatforms
            ) else "",
            lastViewedTimestamp = clock.now().toEpochMilliseconds()
        )
    }
}