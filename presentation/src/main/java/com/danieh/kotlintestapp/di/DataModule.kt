package com.danieh.kotlintestapp.di

import com.danieh.data.datasource.NetworkDataSource
import com.danieh.data.datasource.network.CharactersNetworkDataSource
import com.danieh.data.repository.CharactersRepositoryImpl
import com.danieh.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
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
    internal fun provideNetworkDataSource(retrofit: Retrofit): NetworkDataSource {
        return CharactersNetworkDataSource(retrofit)
    }
}