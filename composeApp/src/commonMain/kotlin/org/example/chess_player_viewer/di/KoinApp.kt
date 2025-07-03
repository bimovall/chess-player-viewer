package org.example.chess_player_viewer.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.includes


val appModule = listOf(provideHttpClient, provideDataSourceModule, viewModelModule)

fun initKoin(config: KoinConfiguration? = null) {
    startKoin {
        includes(config)
        modules(appModule)
    }
}