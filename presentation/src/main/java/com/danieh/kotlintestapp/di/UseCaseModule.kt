package com.danieh.kotlintestapp.di

import com.danieh.domain.executor.SchedulerProvider
import com.danieh.domain.repository.CharactersRepository
import com.danieh.domain.usecase.GetCharactersUseCaseImpl
import com.danieh.domain.usecase.definition.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by danieh
 */
@Module
class UseCaseModule {

    @Singleton
    @Provides
    internal fun provideGetCharactersUseCase(schedulerProvider: SchedulerProvider, repository: CharactersRepository): GetCharactersUseCase {
        return GetCharactersUseCaseImpl(schedulerProvider, repository)
    }
}
