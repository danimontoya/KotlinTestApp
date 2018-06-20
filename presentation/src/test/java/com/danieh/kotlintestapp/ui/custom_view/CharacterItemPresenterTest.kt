package com.danieh.kotlintestapp.ui.custom_view

import com.danieh.kotlintestapp.model.CharacterViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by danieh
 */
class CharacterItemPresenterTest {

    lateinit var presenter: CharacterItemPresenter
    lateinit var model: CharacterViewModel

    @Mock
    private lateinit var view: CharacterItemView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = CharacterItemPresenter(view)
    }

    @Test
    fun `when binding the view model the name should be displayed`() {
        model = createCharacterItemView()

        presenter.bindModel(model)

        verify(view).displayName(model.name)
    }

    @Test
    fun `when binding the view model fav icon should be displayed`() {
        model = createCharacterItemView()

        presenter.bindModel(model)

        verify(view).displayFav(model.saved)
    }

    @Test
    fun `when fav icon is clicked the character should be saved and should be notified that saved has changed`() {
        model = createCharacterItemView()
        presenter.model = model

        presenter.favoriteClick()

        verify(view).displayFav(model.saved)
        verify(view).notifyFavClicked(model)
    }

    @Test
    fun `when item is clicked should be notified`() {
        model = createCharacterItemView()
        presenter.model = model

        presenter.rootClick()

        verify(view).notifyRootClicked(model)
    }


    private fun createCharacterItemView(): CharacterViewModel {

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
        val split = url.split("/")
        val id = split[split.lastIndex - 1].toLong()

        val characterViewModel = CharacterViewModel(id = id,
                name = name,
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
                url = url,
                saved = false)

        return characterViewModel
    }

}