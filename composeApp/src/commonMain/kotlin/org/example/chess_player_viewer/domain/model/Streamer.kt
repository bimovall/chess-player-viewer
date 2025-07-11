package org.example.chess_player_viewer.domain.model

import org.example.chess_player_viewer.data.remote.dto.StreamersDto

data class Streamer(
    val username: String,
    val avatar: String,
    val isCommunityStreamer: Boolean,
    val isLive: Boolean,
    val platforms: List<StreamPlatform>
) {
    data class StreamPlatform(
        val type: String,
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
        Streamer.StreamPlatform(
            type = it.type.orEmpty(),
            channelUrl = it.channelUrl.orEmpty(),
            liveUrl = it.streamUrl.orEmpty()
        )
    }
)