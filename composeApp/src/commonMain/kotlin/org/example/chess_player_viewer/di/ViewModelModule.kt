package org.example.chess_player_viewer.di

import org.example.chess_player_viewer.ui.feature.favorite_player.FavoritePlayerViewModel
import org.example.chess_player_viewer.ui.feature.home.HomeViewModel
import org.example.chess_player_viewer.ui.feature.leaderboard.LeaderboardViewModel
import org.example.chess_player_viewer.ui.feature.profile.ProfileViewModel
import org.example.chess_player_viewer.ui.feature.streamer.StreamerViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val viewModelModule = module {
    viewModelOf(::LeaderboardViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::StreamerViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::FavoritePlayerViewModel)
}