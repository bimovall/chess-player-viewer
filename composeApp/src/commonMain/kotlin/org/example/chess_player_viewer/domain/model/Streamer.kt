package org.example.chess_player_viewer.domain.model

import chessplayerviewer.composeapp.generated.resources.Res
import chessplayerviewer.composeapp.generated.resources.ic_location
import chessplayerviewer.composeapp.generated.resources.ic_twitch
import chessplayerviewer.composeapp.generated.resources.ic_youtube
import org.example.chess_player_viewer.data.remote.dto.StreamersDto
import org.example.chess_player_viewer.utils.StreamType
import org.jetbrains.compose.resources.DrawableResource

data class Streamer(
    val username: String,
    val avatar: String,
    val isCommunityStreamer: Boolean,
    val isLive: Boolean,
    val platforms: List<StreamPlatform>
) {
    data class StreamPlatform(
        val type: String,
        val platformName: String,
        val imageResource: DrawableResource,
        val channelUrl: String,
        val liveUrl: String
    )
}


fun StreamersDto.Streamer.mapToStreamer() = Streamer(
    username = this.username.orEmpty(),
    avatar = this.avatar.orEmpty(),
    isCommunityStreamer = this.isCommunityStreamer ?: false,
    isLive = this.isLive ?: false,
    platforms = this.platforms.map {
        val streamType = StreamType.fromString(it.type) // Convert the API string to StreamType enum
        Streamer.StreamPlatform(
            type = streamType.name, // Use the actual string value from the enum
            platformName = when (streamType) { // Use the enum instance directly in when
                StreamType.TWITCH -> "Twitch"
                StreamType.YOUTUBE -> "Youtube"
                StreamType.UNKNOWN -> "Livestream" // Handle unknown types
            },
            imageResource = when (streamType) { // Use the enum instance directly in when
                StreamType.TWITCH -> Res.drawable.ic_twitch
                StreamType.YOUTUBE -> Res.drawable.ic_youtube
                StreamType.UNKNOWN -> Res.drawable.ic_location // Handle unknown types
            },
            channelUrl = it.channelUrl.orEmpty(),
            liveUrl = it.streamUrl.orEmpty()
        )
    }
)