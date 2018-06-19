package com.danieh.data.datasource.network

import com.danieh.data.datasource.CharactersApi
import com.danieh.data.datasource.NetworkDataSource
import com.danieh.data.model.CharactersConfigurationWS
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by danieh
 */
@Singleton
class CharactersNetworkDataSource
@Inject
constructor(private val client: CharactersApi) : NetworkDataSource {

    override fun getCharactersConfiguration(page: Int): Single<CharactersConfigurationWS> {
        return client.getCharacters(page)
    }

}
