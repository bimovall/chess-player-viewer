package org.example.chess_player_viewer.data.local.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.example.chess_player_viewer.ChessPlayerDB

actual class DatabaseDriverFactory{
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(ChessPlayerDB.Schema, "chess_player.db")
    }
}