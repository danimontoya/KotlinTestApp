package com.danieh.domain.usecase

import com.danieh.domain.executor.SchedulerProvider
import com.danieh.domain.model.CharactersConfigurationModel
import com.danieh.domain.repository.CharactersRepository
import com.danieh.domain.usecase.definition.GetCharactersUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by danieh
 */
class GetCharactersUseCaseTest {

    @Mock
    lateinit var schedulerProvider: SchedulerProvider

    @Mock
    lateinit var repository: CharactersRepository

    lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getCharactersUseCase = GetCharactersUseCaseImpl(schedulerProvider, repository)
        given(schedulerProvider.io()).willReturn(Schedulers.io())
        given(schedulerProvider.ui()).willReturn(AndroidSchedulers.mainThread())
    }

    @Test
    fun `get characters configuration page should retrieve the list of characters `() {
        val page = 1
        val charactersConfigurationModel = CharactersConfigurationModel(1, null, null, emptyList())
        val fakeSingleCharactersConfigurationModel = Single.just<CharactersConfigurationModel>(charactersConfigurationModel)
        given(repository.getCharactersNetwork(page)).willReturn(fakeSingleCharactersConfigurationModel)

        getCharactersUseCase.execute(page)

        verify(repository).getCharactersNetwork(page)
    }
}