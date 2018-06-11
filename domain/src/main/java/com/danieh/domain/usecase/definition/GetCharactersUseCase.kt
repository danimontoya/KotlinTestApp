package com.danieh.domain.usecase.definition

import com.danieh.domain.model.CharactersConfigurationModel
import io.reactivex.Single

/**
 * Created by danieh
 */
interface GetCharactersUseCase {

    fun execute(page: Int): Single<CharactersConfigurationModel>
}