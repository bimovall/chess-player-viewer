package org.example.chess_player_viewer.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StreamersDto(
    @SerialName("streamers") var streamers: ArrayList<Streamer> = arrayListOf()
) {

    @Serializable
    data class Streamer(
        @SerialName("username") var username: String? = null,
        @SerialName("avatar") var avatar: String? = null,
        @SerialName("twitch_url") var twitchUrl: String? = null,
        @SerialName("url") var url: String? = null,
        @SerialName("is_live") var isLive: Boolean? = null,
        @SerialName("is_community_streamer") var isCommunityStreamer: Boolean? = null,
        @SerialName("platforms") var platforms: ArrayList<Platform> = arrayListOf()
    ) {
        @Serializable
        data class Platform(
            @SerialName("type") var type: String? = null,
            @SerialName("stream_url") var streamUrl: String? = null,
            @SerialName("channel_url") var channelUrl: String? = null,
            @SerialName("is_live") var isLive: Boolean? = null,
            @SerialName("is_main_live_platform") var isMainLivePlatform: Boolean? = null

        )
    }
}