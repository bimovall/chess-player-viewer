package org.example.chess_player_viewer.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(

    @SerialName("avatar") var avatar: String? = null,
    @SerialName("player_id") var playerId: Int? = null,
    @SerialName("@id") var id: String? = null,
    @SerialName("url") var url: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("username") var username: String? = null,
    @SerialName("title") var title: String? = null,
    @SerialName("followers") var followers: Int? = null,
    @SerialName("country") var country: String? = null,
    @SerialName("location") var location: String? = null,
    @SerialName("last_online") var lastOnline: Long? = null,
    @SerialName("joined") var joined: Long? = null,
    @SerialName("status") var status: String? = null,
    @SerialName("is_streamer") var isStreamer: Boolean? = null,
    @SerialName("twitch_url") var twitchUrl: String? = null,
    @SerialName("verified") var verified: Boolean? = null,
    @SerialName("league") var league: String? = null,
    @SerialName("streaming_platforms") var streamingPlatforms: ArrayList<StreamingPlatform> = arrayListOf()

) {
    @Serializable
    data class StreamingPlatform(

        @SerialName("type") var type: String? = null,
        @SerialName("channel_url") var channelUrl: String? = null

    )
}