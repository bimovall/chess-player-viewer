package org.example.chess_player_viewer

import android.app.Application
import org.example.chess_player_viewer.di.initKoin

class ChessApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}