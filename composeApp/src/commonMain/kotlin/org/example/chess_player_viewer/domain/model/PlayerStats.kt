package org.example.chess_player_viewer.domain.model

import org.example.chess_player_viewer.data.remote.dto.PlayerStatsDto
import org.example.chess_player_viewer.domain.model.PlayerStats.RecordStats

data class PlayerStats(
    val listRecord: List<RecordStats>
) {
    data class RecordStats(
        val name: String,
        val lastRating: Int = 0
    )
}


fun PlayerStatsDto.mapToPlayerStats(): PlayerStats {
    val chessDaily = RecordStats(
        name = "Daily",
        lastRating = this.chessDaily?.last?.rating ?: 0
    )
    val chess960Daily = RecordStats(
        name = "960",
        lastRating = this.chess960Daily?.last?.rating ?: 0
    )
    val chessRapid = RecordStats(
        name = "Rapid",
        lastRating = this.chessRapid?.last?.rating ?: 0
    )
    val chessBullet = RecordStats(
        name = "Bullet",
        lastRating = this.chessBullet?.last?.rating ?: 0
    )
    val chessBlitz = RecordStats(
        name = "Blitz",
        lastRating = this.chessBlitz?.last?.rating ?: 0
    )
    val fide = RecordStats(
        name = "Fide",
        lastRating = this.fide ?: 0
    )

    return PlayerStats(
        listRecord = listOf(fide, chessBlitz, chessRapid, chessBullet, chess960Daily, chessDaily)
    )
}