package org.example.chess_player_viewer.data.local.driver

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.chess_player_viewer.ChessPlayerDB

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(ChessPlayerDB.Schema, context, "chess_player.db")
    }
}