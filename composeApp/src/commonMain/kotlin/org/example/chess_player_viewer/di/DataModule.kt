package org.example.chess_player_viewer.di

import org.example.chess_player_viewer.data.remote.RemoteSource
import org.example.chess_player_viewer.data.remote.RemoteSourceImpl
import org.example.chess_player_viewer.domain.repository.LeaderboardRepository
import org.example.chess_player_viewer.data.repository.LeaderboardRepositoryImpl
import org.example.chess_player_viewer.data.repository.ProfileRepositoryImpl
import org.example.chess_player_viewer.data.service.ApiService
import org.example.chess_player_viewer.domain.repository.ProfileRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val provideDataSourceModule = module {
    singleOf(::RemoteSourceImpl) {
        bind<RemoteSource>()
    }
    singleOf(::LeaderboardRepositoryImpl) {
        bind<LeaderboardRepository>()
    }
    singleOf(::ProfileRepositoryImpl) {
        bind<ProfileRepository>()
    }
    singleOf(::ApiService)
}