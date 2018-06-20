package com.danieh.kotlintestapp.ui.custom_view

import android.support.annotation.NonNull
import com.danieh.kotlintestapp.model.CharacterViewModel

/**
 * Created by danieh
 */
interface CharacterItemView {

    interface Listener {
        fun bindModel(@NonNull model: CharacterViewModel)

        fun favoriteClick()

        fun rootClick()
    }

    fun displayName(name: String)

    fun displayFav(saved: Boolean)

    fun notifyFavClicked(model: CharacterViewModel)

    fun notifyRootClicked(model: CharacterViewModel)
}