package com.danieh.data.mapper

import com.danieh.data.mapper.base.Mapper
import com.danieh.data.model.CharactersConfigurationWS
import com.danieh.domain.model.CharactersConfigurationModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by danieh
 */
@Singleton
class CharactersMapper
@Inject
constructor(private val mapper: CharacterMapper) : Mapper<CharactersConfigurationWS, CharactersConfigurationModel>() {

    override fun map(from: CharactersConfigurationWS): CharactersConfigurationModel {

        val count = from.count
        val next = from.next
        val previous = from.previous
        val characters = mapper.map(from.results)

        return CharactersConfigurationModel(count, next, previous, characters)
    }
}