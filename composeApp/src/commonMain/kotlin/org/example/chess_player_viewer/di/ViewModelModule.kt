package org.example.chess_player_viewer.di

import org.example.chess_player_viewer.ui.feature.leaderboard.LeaderboardViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val viewModelModule = module {
    viewModelOf(::LeaderboardViewModel)
}