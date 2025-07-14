package org.example.chess_player_viewer

import android.app.Application
import org.example.chess_player_viewer.di.androidModule
import org.example.chess_player_viewer.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.KoinConfiguration

class ChessApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(config = KoinConfiguration {
            androidContext(this@ChessApplication)
        }, androidModule())
    }
}