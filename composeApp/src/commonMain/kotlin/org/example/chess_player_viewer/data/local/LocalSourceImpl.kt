package org.example.chess_player_viewer.data.local

import kotlinx.serialization.json.Json
import org.example.chess_player_viewer.ChessPlayerDB
import org.example.chess_player_viewer.data.local.entity.FavoritePlayerEntity
import org.example.chess_player_viewer.data.local.entity.ProfileEntity
import org.example.chess_player_viewer.data.local.entity.StreamingPlatformEntity
import org.example.chess_player_viewer.utils.ErrorHandler
import org.example.chess_player_viewer.utils.Result

class LocalSourceImpl(private val database: ChessPlayerDB) : LocalSource {
    override fun getAllRecentlyViewedProfiles(): Result<List<ProfileEntity>> {
        return try {
            Result.Success(
                database.recentlyViewedProfilesQueries.selectLatest15().executeAsList().map {
                    val platform: List<StreamingPlatformEntity> =
                        if (it.streamingPlatformsJson?.isNotBlank() == true) Json.decodeFromString(
                            it.streamingPlatformsJson
                        ) else listOf()
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
                })
        } catch (e: Exception) {
            Result.Error(ErrorHandler.UnknownError(e.message.orEmpty()))
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

    override fun getAllFavoritePlayer(): Result<List<FavoritePlayerEntity>> {
        return try {
            Result.Success(
                database.favoritePlayerQueries.selectAllFavoritePlayer().executeAsList().map {
                    FavoritePlayerEntity(
                        playerId = it.playerId,
                        username = it.username.orEmpty(),
                        name = it.name.orEmpty(),
                        title = it.title.orEmpty(),
                        avatar = it.avatar.orEmpty()
                    )
                })
        } catch (e: Exception) {
            Result.Error(ErrorHandler.UnknownError(e.message.orEmpty()))
        }
    }

    override fun getFavoritePlayerByUsername(username: String): Result<FavoritePlayerEntity?> {

        return try {
            val player = database.favoritePlayerQueries.selectFavoritePlayerByUsername(username)
                .executeAsOneOrNull()
            if (player != null) {
                Result.Success(
                    FavoritePlayerEntity(
                        playerId = player.playerId,
                        username = player.username.orEmpty(),
                        name = player.name.orEmpty(),
                        title = player.title.orEmpty(),
                        avatar = player.avatar.orEmpty()
                    )
                )
            } else {
                Result.Success(data = null)
            }
        } catch (e: Exception) {
            Result.Error(ErrorHandler.UnknownError(e.message.orEmpty()))
        }
    }

    override fun insertFavoritePlayer(player: FavoritePlayerEntity): Result<Long> {
        return try {
            database.favoritePlayerQueries.insertFavoritePlayer(
                playerId = player.playerId,
                username = player.username,
                name = player.name,
                title = player.title,
                avatar = player.avatar
            )

            val id = database.favoritePlayerQueries.lastInsertId().executeAsOneOrNull()
            if (id != null) {
                Result.Success(id)
            } else {
                Result.Error(ErrorHandler.NotFound("Data not found"))
            }
        } catch (e: Exception) {
            Result.Error(ErrorHandler.UnknownError(e.message.orEmpty()))
        }

    }

    override fun deleteFavoritePlayer(username: String): Result<Boolean> {
        return try {
            database.favoritePlayerQueries.deleteFavoritePlayer(username)
            val result = database.favoritePlayerQueries.selectFavoritePlayerByUsername(username)
                .executeAsOneOrNull()
            if (result == null) {
                Result.Success(true)
            } else {
                Result.Success(false)
            }
        } catch (e: Exception) {
            Result.Error(ErrorHandler.UnknownError(e.message.orEmpty()))
        }
    }
}