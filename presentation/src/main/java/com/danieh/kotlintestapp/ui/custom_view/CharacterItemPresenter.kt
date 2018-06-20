package com.danieh.kotlintestapp.ui.custom_view

import com.danieh.kotlintestapp.model.CharacterViewModel

/**
 * Created by danieh
 */
class CharacterItemPresenter(private val view: CharacterItemView?) : CharacterItemView.Listener {

    lateinit var model: CharacterViewModel

    override fun bindModel(model: CharacterViewModel) {
        this.model = model

        view?.displayName(model.name)
        view?.displayFav(model.saved)
    }

    override fun favoriteClick() {
        model.saved = !model.saved

        view?.displayFav(model.saved)
        view?.notifyFavClicked(model)
    }

    override fun rootClick() {
        view?.notifyRootClicked(model)
    }
}