package org.example.chess_player_viewer.di

import org.example.chess_player_viewer.data.remote.RemoteSource
import org.example.chess_player_viewer.data.remote.RemoteSourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val provideDataSourceModule = module {
    singleOf(::RemoteSourceImpl) {
        bind<RemoteSource>()
    }
}