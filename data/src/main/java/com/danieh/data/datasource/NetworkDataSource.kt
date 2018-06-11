package com.danieh.data.datasource

import com.danieh.data.model.CharactersConfigurationWS
import io.reactivex.Single

/**
 * Created by danieh
 */
interface NetworkDataSource {
    fun getCharactersConfiguration(page: Int): Single<CharactersConfigurationWS>
}