package org.example.chess_player_viewer.di

import org.example.chess_player_viewer.domain.usecase.GetLeaderboardUseCase
import org.example.chess_player_viewer.domain.usecase.GetProfilePlayerStatsUseCase
import org.example.chess_player_viewer.domain.usecase.GetStreamerUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val provideDomainModule = module {

    factoryOf(::GetLeaderboardUseCase)

    factoryOf(::GetProfilePlayerStatsUseCase)

    factoryOf(::GetStreamerUseCase)
}