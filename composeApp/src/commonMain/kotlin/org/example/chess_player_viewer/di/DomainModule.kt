package org.example.chess_player_viewer.di

import org.example.chess_player_viewer.domain.usecase.GetLeaderboardUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val provideDomainModule = module {

    singleOf(::GetLeaderboardUseCase)
}