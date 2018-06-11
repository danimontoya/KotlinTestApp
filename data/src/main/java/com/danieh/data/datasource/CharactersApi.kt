package com.danieh.data.datasource

import com.danieh.data.model.CharactersConfigurationWS
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by danieh
 */
interface CharactersApi {

    @GET("?format=json")
    fun getCharacters(@Query("page") page: Int): Single<CharactersConfigurationWS>
}