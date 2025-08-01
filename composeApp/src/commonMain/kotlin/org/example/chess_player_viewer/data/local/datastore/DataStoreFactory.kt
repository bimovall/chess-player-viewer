package org.example.chess_player_viewer.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

internal const val databaseName = "chess_player_viewer.preferences_pb"

expect class DataStoreFactory {
    fun createDataStore(filename: String): DataStore<Preferences>
}