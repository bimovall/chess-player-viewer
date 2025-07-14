package org.example.chess_player_viewer

import androidx.compose.ui.window.ComposeUIViewController
import org.example.chess_player_viewer.di.initKoin
import org.example.chess_player_viewer.di.iosModule
import org.koin.dsl.KoinConfiguration

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin(additionalModule = arrayOf(iosModule()))
    }
) {
    App()
}