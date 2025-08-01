package org.example.chess_player_viewer.di

import app.cash.sqldelight.db.SqlDriver
import org.example.chess_player_viewer.ChessPlayerDB
import org.example.chess_player_viewer.data.local.LocalSource
import org.example.chess_player_viewer.data.local.LocalSourceImpl
import org.example.chess_player_viewer.data.local.driver.DatabaseDriverFactory
import org.example.chess_player_viewer.data.remote.RemoteSource
import org.example.chess_player_viewer.data.remote.RemoteSourceImpl
import org.example.chess_player_viewer.domain.repository.LeaderboardRepository
import org.example.chess_player_viewer.data.repository.LeaderboardRepositoryImpl
import org.example.chess_player_viewer.data.repository.ProfileRepositoryImpl
import org.example.chess_player_viewer.data.repository.StreamerRepositoryImpl
import org.example.chess_player_viewer.data.service.ApiService
import org.example.chess_player_viewer.domain.repository.ProfileRepository
import org.example.chess_player_viewer.domain.repository.StreamerRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
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
    singleOf(::StreamerRepositoryImpl) {
        bind<StreamerRepository>()
    }
    singleOf(::ApiService)

    singleOf(::LocalSourceImpl) {
        bind<LocalSource>()
    }

    single<ChessPlayerDB> {
        ChessPlayerDB(get<SqlDriver>())
    }

    single<Clock> { Clock.System }
}