package org.example.chess_player_viewer.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.includes


val appModule = listOf(provideHttpClient, provideDataSourceModule, viewModelModule, provideDomainModule)

fun initKoin(config: KoinConfiguration? = null, vararg additionalModule: Module) {
    startKoin {
        includes(config)
        modules(appModule + additionalModule)
    }
}