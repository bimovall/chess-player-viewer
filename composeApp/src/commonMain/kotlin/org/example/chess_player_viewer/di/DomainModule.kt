package org.example.chess_player_viewer.di

import org.example.chess_player_viewer.domain.usecase.GetAllFavoritePlayerUseCase
import org.example.chess_player_viewer.domain.usecase.GetFavoritePlayerByPlayerIdUseCase
import org.example.chess_player_viewer.domain.usecase.GetLeaderboardUseCase
import org.example.chess_player_viewer.domain.usecase.GetProfilePlayerStatsUseCase
import org.example.chess_player_viewer.domain.usecase.GetRecentlyViewedProfilesUseCase
import org.example.chess_player_viewer.domain.usecase.GetStreamerUseCase
import org.example.chess_player_viewer.domain.usecase.InsertFavoritePlayer
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val provideDomainModule = module {

    factoryOf(::GetLeaderboardUseCase)

    factoryOf(::GetProfilePlayerStatsUseCase)

    factoryOf(::GetStreamerUseCase)

    factoryOf(::GetRecentlyViewedProfilesUseCase)

    factoryOf(::GetAllFavoritePlayerUseCase)

    factoryOf(::GetFavoritePlayerByPlayerIdUseCase)

    factoryOf(::InsertFavoritePlayer)
}