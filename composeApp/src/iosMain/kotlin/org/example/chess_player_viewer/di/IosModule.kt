package org.example.chess_player_viewer.di

import app.cash.sqldelight.db.SqlDriver
import org.example.chess_player_viewer.data.local.driver.DatabaseDriverFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun iosModule() = module {

    singleOf(::DatabaseDriverFactory)

    single<SqlDriver> {
        get <DatabaseDriverFactory>().createDriver()
    }
}