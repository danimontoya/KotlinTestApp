package com.danieh.data.mapper

import com.danieh.data.mapper.base.Mapper
import com.danieh.data.model.CharacterWS
import com.danieh.domain.model.CharacterModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by danieh
 */
@Singleton
class CharacterMapper
@Inject
constructor() : Mapper<CharacterWS, CharacterModel>() {

    companion object {
        var UNKOWN = "unkown"
        var EMPTY = "--"
    }

    override fun map(from: CharacterWS): CharacterModel {

        val name = from.name ?: UNKOWN
        val height = from.height ?: EMPTY
        val mass = from.mass ?: EMPTY
        val hair_color = from.hair_color ?: EMPTY
        val skin_color = from.skin_color ?: EMPTY
        val eye_color = from.eye_color ?: EMPTY
        val birth_year = from.birth_year ?: EMPTY
        val gender = from.gender ?: EMPTY
        val homeworld = from.homeworld ?: EMPTY
        val films = from.films ?: emptyList()
        val species = from.species ?: emptyList()
        val vehicles = from.vehicles ?: emptyList()
        val starships = from.starships ?: emptyList()
        val created = from.created ?: EMPTY
        val edited = from.edited ?: EMPTY
        val url = from.url ?: EMPTY

        return CharacterModel(name, height, mass, hair_color, skin_color, eye_color, birth_year, gender, homeworld, films, species, vehicles,
                starships, created, edited, url)
    }
}