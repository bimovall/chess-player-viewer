package org.example.chess_player_viewer

import androidx.compose.ui.window.ComposeUIViewController
import org.example.chess_player_viewer.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}