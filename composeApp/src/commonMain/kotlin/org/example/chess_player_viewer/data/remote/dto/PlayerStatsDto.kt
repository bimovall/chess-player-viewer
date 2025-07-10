package org.example.chess_player_viewer.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PlayerStatsDto(
    @SerialName("chess_daily") var chessDaily: ChessStats? = ChessStats(),
    @SerialName("chess960_daily") var chess960Daily: ChessStats? = ChessStats(),
    @SerialName("chess_rapid") var chessRapid: ChessStats? = ChessStats(),
    @SerialName("chess_bullet") var chessBullet: ChessStats? = ChessStats(),
    @SerialName("chess_blitz") var chessBlitz: ChessStats? = ChessStats(),
    @SerialName("fide") var fide: Int? = null,
    @SerialName("tactics") var tactics: ChessStats? = ChessStats(),
    @SerialName("puzzle_rush") var puzzleRush: ChessStats? = ChessStats()

) {
    @Serializable
    data class ChessStats(
        @SerialName("last") var last: Last? = Last(),
        @SerialName("best") var best: Best? = Best(),
        @SerialName("record") var record: Record? = Record()
    )


    @Serializable
    data class Last(
        @SerialName("rating") var rating: Int? = null,
        @SerialName("date") var date: Int? = null,
        @SerialName("rd") var rd: Int? = null

    )

    @Serializable
    data class Best(
        @SerialName("rating") var rating: Int? = null,
        @SerialName("date") var date: Int? = null,
        @SerialName("game") var game: String? = null

    )

    @Serializable
    data class Record(
        @SerialName("win") var win: Int? = null,
        @SerialName("loss") var loss: Int? = null,
        @SerialName("draw") var draw: Int? = null,
        @SerialName("time_per_move") var timePerMove: Int? = null,
        @SerialName("timeout_percent") var timeoutPercent: Int? = null

    )
}