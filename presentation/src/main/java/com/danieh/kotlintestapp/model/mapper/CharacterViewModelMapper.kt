package com.danieh.kotlintestapp.model.mapper

import com.danieh.data.mapper.base.Mapper
import com.danieh.domain.model.CharacterModel
import com.danieh.kotlintestapp.model.CharacterViewModel

/**
 * Created by danieh
 */
class CharacterViewModelMapper constructor() : Mapper<CharacterModel, CharacterViewModel>() {

    override fun map(from: CharacterModel): CharacterViewModel {

        val split = from.url.split("/")
        val id = split[split.lastIndex - 1].toLong()

        val name = from.name
        val height = from.height
        val mass = from.mass
        val hair_color = from.hair_color
        val skin_color = from.skin_color
        val eye_color = from.eye_color
        val birth_year = from.birth_year
        val gender = from.gender
        val homeworld = from.homeworld
        val films = from.films
        val species = from.species
        val vehicles = from.vehicles
        val starships = from.starships
        val created = from.created
        val edited = from.edited
        val url = from.url

        return CharacterViewModel(id, name, height, mass, hair_color, skin_color, eye_color, birth_year, gender, homeworld, films, species, vehicles,
                starships, created, edited, url)
    }
}