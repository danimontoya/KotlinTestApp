package com.danieh.data.mapper

import com.danieh.data.model.CharactersConfigurationWS
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by danieh
 */
class CharactersMapperTest {

    private val JSON = "{\n" +
            "\t\"count\": 87,\n" +
            "\t\"next\": \"https://swapi.co/api/people/?page=2\",\n" +
            "\t\"previous\": null,\n" +
            "\t\"results\": [{\n" +
            "\t\t\t\"name\": \"Luke Skywalker\",\n" +
            "\t\t\t\"height\": \"172\",\n" +
            "\t\t\t\"mass\": \"77\",\n" +
            "\t\t\t\"hair_color\": \"blond\",\n" +
            "\t\t\t\"skin_color\": \"fair\",\n" +
            "\t\t\t\"eye_color\": \"blue\",\n" +
            "\t\t\t\"birth_year\": \"19BBY\",\n" +
            "\t\t\t\"gender\": \"male\",\n" +
            "\t\t\t\"homeworld\": \"https://swapi.co/api/planets/1/\",\n" +
            "\t\t\t\"films\": [\n" +
            "\t\t\t\t\"https://swapi.co/api/films/2/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/6/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/3/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/1/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/7/\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"species\": [\n" +
            "\t\t\t\t\"https://swapi.co/api/species/1/\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"vehicles\": [\n" +
            "\t\t\t\t\"https://swapi.co/api/vehicles/14/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/vehicles/30/\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"starships\": [\n" +
            "\t\t\t\t\"https://swapi.co/api/starships/12/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/starships/22/\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"created\": \"2014-12-09T13:50:51.644000Z\",\n" +
            "\t\t\t\"edited\": \"2014-12-20T21:17:56.891000Z\",\n" +
            "\t\t\t\"url\": \"https://swapi.co/api/people/1/\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"name\": \"C-3PO\",\n" +
            "\t\t\t\"height\": \"167\",\n" +
            "\t\t\t\"mass\": \"75\",\n" +
            "\t\t\t\"hair_color\": \"n/a\",\n" +
            "\t\t\t\"skin_color\": \"gold\",\n" +
            "\t\t\t\"eye_color\": \"yellow\",\n" +
            "\t\t\t\"birth_year\": \"112BBY\",\n" +
            "\t\t\t\"gender\": \"n/a\",\n" +
            "\t\t\t\"homeworld\": \"https://swapi.co/api/planets/1/\",\n" +
            "\t\t\t\"films\": [\n" +
            "\t\t\t\t\"https://swapi.co/api/films/2/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/5/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/4/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/6/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/3/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/1/\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"species\": [\n" +
            "\t\t\t\t\"https://swapi.co/api/species/2/\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"vehicles\": [],\n" +
            "\t\t\t\"starships\": [],\n" +
            "\t\t\t\"created\": \"2014-12-10T15:10:51.357000Z\",\n" +
            "\t\t\t\"edited\": \"2014-12-20T21:17:50.309000Z\",\n" +
            "\t\t\t\"url\": \"https://swapi.co/api/people/2/\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"name\": \"R2-D2\",\n" +
            "\t\t\t\"height\": \"96\",\n" +
            "\t\t\t\"mass\": \"32\",\n" +
            "\t\t\t\"hair_color\": \"n/a\",\n" +
            "\t\t\t\"skin_color\": \"white, blue\",\n" +
            "\t\t\t\"eye_color\": \"red\",\n" +
            "\t\t\t\"birth_year\": \"33BBY\",\n" +
            "\t\t\t\"gender\": \"n/a\",\n" +
            "\t\t\t\"homeworld\": \"https://swapi.co/api/planets/8/\",\n" +
            "\t\t\t\"films\": [\n" +
            "\t\t\t\t\"https://swapi.co/api/films/2/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/5/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/4/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/6/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/3/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/1/\",\n" +
            "\t\t\t\t\"https://swapi.co/api/films/7/\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"species\": [\n" +
            "\t\t\t\t\"https://swapi.co/api/species/2/\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"vehicles\": [],\n" +
            "\t\t\t\"starships\": [],\n" +
            "\t\t\t\"created\": \"2014-12-10T15:11:50.376000Z\",\n" +
            "\t\t\t\"edited\": \"2014-12-20T21:17:50.311000Z\",\n" +
            "\t\t\t\"url\": \"https://swapi.co/api/people/3/\"\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}"

    private lateinit var characterMapper: CharacterMapper
    private lateinit var charactersMapper: CharactersMapper

    @Before
    fun `setup`() {
        characterMapper = CharacterMapper()
        charactersMapper = CharactersMapper(characterMapper)
    }

    @Test
    fun `characters data model should map to characters domain model`() {
        val gson = Gson()
        val charactersConfigurationWSType = object : TypeToken<CharactersConfigurationWS>() {}.type
        val charactersConfigurationWS = gson.fromJson<CharactersConfigurationWS>(JSON, charactersConfigurationWSType)

        val charactersConfigurationModel = charactersMapper.map(charactersConfigurationWS)

        Assert.assertEquals(charactersConfigurationModel.count, 87)
        Assert.assertEquals(charactersConfigurationModel.next, "https://swapi.co/api/people/?page=2")
        Assert.assertEquals(charactersConfigurationModel.previous, null)
        Assert.assertEquals(charactersConfigurationModel.characters.size, 3)
    }

}