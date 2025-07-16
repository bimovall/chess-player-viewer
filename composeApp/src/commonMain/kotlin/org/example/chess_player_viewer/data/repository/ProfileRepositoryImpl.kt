@file:OptIn(ExperimentalTime::class)

package org.example.chess_player_viewer.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.example.chess_player_viewer.data.local.LocalSource
import org.example.chess_player_viewer.data.local.entity.FavoritePlayerEntity
import org.example.chess_player_viewer.data.local.entity.ProfileEntity
import org.example.chess_player_viewer.data.remote.RemoteSource
import org.example.chess_player_viewer.domain.model.FavoritePlayer
import org.example.chess_player_viewer.domain.model.PlayerStats
import org.example.chess_player_viewer.domain.model.Profile
import org.example.chess_player_viewer.domain.model.mapToFavoritePlayer
import org.example.chess_player_viewer.domain.model.mapToPlayerStats
import org.example.chess_player_viewer.domain.model.mapToProfile
import org.example.chess_player_viewer.domain.model.mapToProfileEntity
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
                insertProfileToRecentlyViewed(profile.mapToProfileEntity())
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

    override fun getRecentlyViewedProfiles(count: Int): Flow<Result<List<Profile>>> {
        return flow {
            emit(localSource.getAllRecentlyViewedProfiles().mapSuccess { entity ->
                entity.map { it.mapToProfile() }
            })
        }
    }

    override fun getAllFavoritePlayers(): Flow<Result<List<FavoritePlayer>>> {
        return flow {
            emit(
                localSource.getAllFavoritePlayer().mapSuccess { entity ->
                    entity.map { it.mapToFavoritePlayer() }
                }
            )
        }
    }

    override fun getFavoritePlayerByPlayerId(playerId: Long): Flow<Result<FavoritePlayer>> {
        return flow {
            emit(
                localSource.getFavoritePlayerByPlayerId(playerId).mapSuccess {
                    it.mapToFavoritePlayer()
                }
            )
        }
    }

    override fun insertFavoritePlayer(player: FavoritePlayerEntity): Flow<Result<Long>> {
        return flow {
            emit(localSource.insertFavoritePlayer(player))
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun insertProfileToRecentlyViewed(profile: ProfileEntity) {
        localSource.insertRecentlyViewedPlayer(
            playerId = profile.playerId ?: 0L,
            username = profile.username.orEmpty(),
            name = profile.name.orEmpty(),
            title = profile.title.orEmpty(),
            avatar = profile.avatar.orEmpty(),
            followers = profile.followers ?: 0L,
            country = profile.country.orEmpty(),
            lastOnline = profile.lastOnline ?: 0L,
            joined = profile.joined ?: 0L,
            location = profile.location.orEmpty(),
            league = profile.league.orEmpty(),
            isStreamer = profile.isStreamer ?: false,
            streamingPlatformsJson = if (profile.streamingPlatforms.isNotEmpty()) Json.encodeToString(
                profile.streamingPlatforms
            ) else "",
            lastViewedTimestamp = clock.now().toEpochMilliseconds()
        )
    }
}