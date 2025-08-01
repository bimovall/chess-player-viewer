package org.example.chess_player_viewer.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.db.SqlDriver
import org.example.chess_player_viewer.data.local.datastore.DataStoreFactory
import org.example.chess_player_viewer.data.local.datastore.databaseName
import org.example.chess_player_viewer.data.local.driver.DatabaseDriverFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun androidModule() = module {

    singleOf(::DatabaseDriverFactory)

    singleOf(::DataStoreFactory)

    single<SqlDriver> {
        get <DatabaseDriverFactory>().createDriver()
    }

    single<DataStore<Preferences>> {
        get<DataStoreFactory>().createDataStore(databaseName)
    }
}