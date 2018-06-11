package com.danieh.data.repository

import com.danieh.data.datasource.NetworkDataSource
import com.danieh.data.mapper.CharactersMapper
import com.danieh.domain.model.CharactersConfigurationModel
import com.danieh.domain.repository.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by danieh
 */
@Singleton
class CharactersRepositoryImpl
@Inject
constructor(private val dataSource: NetworkDataSource, private val mapper: CharactersMapper) : CharactersRepository {

    override fun getCharactersNetwork(page: Int): Single<CharactersConfigurationModel> {
        return dataSource.getCharactersConfiguration(page).map(mapper::map)
    }
}