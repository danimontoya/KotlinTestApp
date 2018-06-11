package com.danieh.domain.usecase

import com.danieh.domain.executor.SchedulerProvider
import com.danieh.domain.model.CharactersConfigurationModel
import com.danieh.domain.repository.CharactersRepository
import com.danieh.domain.usecase.definition.GetCharactersUseCase
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by danieh
 */
@Singleton
class GetCharactersUseCaseImpl
@Inject
constructor(private val schedulerProvider: SchedulerProvider,
            private val repository: CharactersRepository) : GetCharactersUseCase {

    override fun execute(page: Int): Single<CharactersConfigurationModel> {
        return repository.getCharactersNetwork(page).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
    }
}