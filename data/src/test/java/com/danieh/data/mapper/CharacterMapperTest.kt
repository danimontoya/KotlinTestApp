package com.danieh.data.mapper

import com.danieh.data.mapper.CharacterMapper.Companion.EMPTY
import com.danieh.data.mapper.CharacterMapper.Companion.UNKOWN
import com.danieh.data.model.CharacterWS
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by danieh
 */
class CharacterMapperTest {

    private lateinit var characterMapper: CharacterMapper

    @Before
    fun `setup`() {
        characterMapper = CharacterMapper()
    }

    @Test
    fun `character data model with null values map to character domain model`() {
        val characterWS = CharacterWS(name = null,
                height = null,
                mass = null,
                hair_color = null,
                skin_color = null,
                eye_color = null,
                birth_year = null,
                gender = null,
                homeworld = null,
                films = null,
                species = null,
                vehicles = null,
                starships = null,
                created = null,
                edited = null,
                url = null)

        val characterModel = characterMapper.map(characterWS)

        Assert.assertEquals(characterModel.name, UNKOWN)
        Assert.assertEquals(characterModel.height, EMPTY)
        Assert.assertEquals(characterModel.mass, EMPTY)
        Assert.assertEquals(characterModel.hair_color, EMPTY)
        Assert.assertEquals(characterModel.skin_color, EMPTY)
        Assert.assertEquals(characterModel.eye_color, EMPTY)
        Assert.assertEquals(characterModel.birth_year, EMPTY)
        Assert.assertEquals(characterModel.gender, EMPTY)
        Assert.assertEquals(characterModel.homeworld, EMPTY)
        Assert.assertEquals(characterModel.films.size, 0)
        Assert.assertEquals(characterModel.species.size, 0)
        Assert.assertEquals(characterModel.vehicles.size, 0)
        Assert.assertEquals(characterModel.starships.size, 0)
        Assert.assertEquals(characterModel.created, EMPTY)
        Assert.assertEquals(characterModel.edited, EMPTY)
        Assert.assertEquals(characterModel.url, EMPTY)
    }

    @Test
    fun `character data model map to character domain model`() {
        val name = "Luke Skywalker"
        val height = "172"
        val mass = "77"
        val hair_color = "blond"
        val skin_color = "fair"
        val eye_color = "blue"
        val birth_year = "19BBY"
        val gender = "male"
        val homeworld = "https://swapi.co/api/planets/1/"
        val films = listOf("https://swapi.co/api/films/2/", "https://swapi.co/api/films/6/")
        val species = listOf("https://swapi.co/api/species/1/")
        val vehicles = listOf("https://swapi.co/api/vehicles/14/", "https://swapi.co/api/vehicles/30/")
        val starships = listOf("https://swapi.co/api/starships/12/", "https://swapi.co/api/starships/22/")
        val created = "2014-12-09T13:50:51.644000Z"
        val edited = "2014-12-20T21:17:56.891000Z"
        val url = "https://swapi.co/api/people/1/"

        val characterWS = CharacterWS(name = name,
                height = height,
                mass = mass,
                hair_color = hair_color,
                skin_color = skin_color,
                eye_color = eye_color,
                birth_year = birth_year,
                gender = gender,
                homeworld = homeworld,
                films = films,
                species = species,
                vehicles = vehicles,
                starships = starships,
                created = created,
                edited = edited,
                url = url)

        val characterModel = characterMapper.map(characterWS)

        Assert.assertEquals(characterModel.name, name)
        Assert.assertEquals(characterModel.height, height)
        Assert.assertEquals(characterModel.mass, mass)
        Assert.assertEquals(characterModel.hair_color, hair_color)
        Assert.assertEquals(characterModel.skin_color, skin_color)
        Assert.assertEquals(characterModel.eye_color, eye_color)
        Assert.assertEquals(characterModel.birth_year, birth_year)
        Assert.assertEquals(characterModel.gender, gender)
        Assert.assertEquals(characterModel.homeworld, homeworld)
        Assert.assertEquals(characterModel.films.size, films.size)
        Assert.assertEquals(characterModel.species.size, species.size)
        Assert.assertEquals(characterModel.vehicles.size, vehicles.size)
        Assert.assertEquals(characterModel.starships.size, starships.size)
        Assert.assertEquals(characterModel.created, created)
        Assert.assertEquals(characterModel.edited, edited)
        Assert.assertEquals(characterModel.url, url)
    }
}