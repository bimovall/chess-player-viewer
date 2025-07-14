package org.example.chess_player_viewer.data.local.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileEntity(
    @SerialName("player_id") var playerId: Int? = null,
    @SerialName("avatar") var avatar: String? = null,
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
    @SerialName("verified") var verified: Boolean? = null,
    @SerialName("league") var league: String? = null,
    @SerialName("streaming_platforms") var streamingPlatforms: ArrayList<StreamingPlatformEntity> = arrayListOf()
)

@Serializable
data class StreamingPlatformEntity(
    @SerialName("type") var type: String? = null,
    @SerialName("channel_url") var channelUrl: String? = null
)