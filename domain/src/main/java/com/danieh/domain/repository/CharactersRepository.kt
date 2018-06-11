package com.danieh.domain.repository

import com.danieh.domain.model.CharactersConfigurationModel
import io.reactivex.Single

/**
 * Created by danieh
 */
interface CharactersRepository {
    fun getCharactersNetwork(page: Int): Single<CharactersConfigurationModel>
}