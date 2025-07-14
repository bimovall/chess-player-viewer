package org.example.chess_player_viewer.data.local

import org.example.chess_player_viewer.ChessPlayerDB
import org.example.chess_player_viewer.data.local.driver.DatabaseDriverFactory

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val driver = databaseDriverFactory.createDriver()
    val database = ChessPlayerDB(driver)
}