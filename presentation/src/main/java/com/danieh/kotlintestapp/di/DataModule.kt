package com.danieh.kotlintestapp.di

import com.danieh.data.datasource.CharactersApi
import com.danieh.data.datasource.NetworkDataSource
import com.danieh.data.datasource.network.CharactersNetworkDataSource
import com.danieh.data.repository.CharactersRepositoryImpl
import com.danieh.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by danieh
 */
@Module
class DataModule {

    @Singleton
    @Provides
    internal fun provideCharactersRepository(repositoryImpl: CharactersRepositoryImpl): CharactersRepository {
        return repositoryImpl
    }

    @Provides
    @Singleton
    internal fun provideCharactersNetworkDataSource(client: CharactersApi): NetworkDataSource {
        return CharactersNetworkDataSource(client)
    }
}