package org.example.chess_player_viewer.domain.model

import kotlinx.serialization.Serializable
import org.example.chess_player_viewer.data.local.entity.ProfileEntity
import org.example.chess_player_viewer.data.local.entity.StreamingPlatformEntity
import org.example.chess_player_viewer.data.remote.dto.ProfileDto

@Serializable
data class Profile(
    val playerId: Long,
    val username: String,
    val name: String,
    val title: String,
    val avatar: String,
    val followers: Long,
    val country: String,
    val lastOnline: Long,
    val joined: Long,
    val location: String,
    val league: String,
    val isStreamer: Boolean,
    val streamingPlatforms: List<StreamingPlatformModel> = listOf()

) {
    @Serializable
    data class StreamingPlatformModel(
        val type: String,
        val url: String
    )
}

fun ProfileDto.mapToProfile() = Profile(
    playerId = this.playerId ?: 0L,
    username = this.username.orEmpty(),
    name = this.name.orEmpty(),
    title = this.title.orEmpty(),
    avatar = this.avatar.orEmpty(),
    followers = this.followers ?: 0L,
    country = this.country.orEmpty(),
    lastOnline = this.lastOnline ?: 0L,
    joined = this.joined ?: 0L,
    location = this.location ?: "-",
    league = this.league.orEmpty(),
    isStreamer = this.isStreamer ?: false,
    streamingPlatforms = this.streamingPlatforms.map {
        Profile.StreamingPlatformModel(
            it.type.orEmpty(),
            it.channelUrl.orEmpty()
        )
    }
)

fun ProfileEntity.mapToProfile() = Profile(
    playerId = this.playerId ?: 0L,
    username = this.username.orEmpty(),
    name = this.name.orEmpty(),
    title = this.title.orEmpty(),
    avatar = this.avatar.orEmpty(),
    followers = this.followers ?: 0L,
    country = this.country.orEmpty(),
    lastOnline = this.lastOnline ?: 0L,
    joined = this.joined ?: 0L,
    location = this.location ?: "-",
    league = this.league.orEmpty(),
    isStreamer = this.isStreamer ?: false,
    streamingPlatforms = this.streamingPlatforms.map {
        Profile.StreamingPlatformModel(
            it.type.orEmpty(),
            it.channelUrl.orEmpty()
        )
    }
)

fun Profile.mapToProfileEntity() = ProfileEntity(
    playerId = this.playerId,
    avatar = this.avatar,
    name = this.name,
    username = this.username,
    title = this.title,
    followers =  this.followers,
    country = this.country,
    location =  this.location,
    lastOnline = this.lastOnline,
    joined = this.joined,
    isStreamer = this.isStreamer,
    league = this.league,
    streamingPlatforms = this.streamingPlatforms.map {
        StreamingPlatformEntity(
            type = it.type,
            channelUrl = it.url
        )
    },
)