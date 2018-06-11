package com.danieh.kotlintestapp.ui.main

import android.support.annotation.NonNull
import com.danieh.kotlintestapp.model.CharacterViewModel

/**
 * Created by danieh
 */
interface MainView {

    fun showLoading()

    fun hideLoading()

    fun showCharacters(@NonNull characters: MutableList<CharacterViewModel>)

    fun removeScrollListener()

    fun canLoadMore()

    fun showLoadingItem()

    fun hideLoadingItem()

    fun showNewCharacter(@NonNull newCharacter: CharacterViewModel)

    fun updateCharacters(characters: MutableList<CharacterViewModel>)
}