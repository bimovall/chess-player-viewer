package org.example.chess_player_viewer.domain.model

import org.example.chess_player_viewer.data.remote.dto.PlayerDto
import org.example.chess_player_viewer.domain.model.Leaderboards.Player

data class Leaderboards(
    val data: Map<String, List<Player>>
) {
    data class Player(
        val playerId: Int,
        val id: String,
        val url: String,
        val username: String,
        val score: Int,
        val rank: Int,
        val name: String,
        val title: String,
        val avatar: String
    )
}

fun mapToLeaderboards(map: Map<String, List<PlayerDto>>): Leaderboards {
    val result = hashMapOf<String, List<Player>>()

    for ((key, value) in map) {
        result[key] = value.map { it.mapToPlayer() }
    }

    return Leaderboards(
        result
    )
}

fun PlayerDto.mapToPlayer(): Leaderboards.Player {
    return Leaderboards.Player(
        playerId = this.playerId,
        id = this.id,
        url = this.url,
        username = this.username,
        score = this.score,
        rank = this.rank,
        title = this.title ?: "",
        name = this.name ?: "",
        avatar = this.avatar
    )
}

val filterLeaderboards = mapOf(
    "daily" to "Daily",
    "daily960" to "Daily 960",
    "live_rapid" to "Rapid",
    "live_blitz" to "Blitz",
    "live_bullet" to "Bullet",
    "live_bughouse" to "Bughouse",
    "live_blitz960" to "Blitz 960",
    "live_threecheck" to "3 Check",
    "live_crazyhouse" to "Crazyhouse",
    "live_kingofthehill" to "King of the Hill",
    "lessons" to "Lessons",
    "tactics" to "Tactics"
)
