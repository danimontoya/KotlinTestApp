package com.danieh.kotlintestapp.ui.main

import com.danieh.domain.model.CharactersConfigurationModel
import com.danieh.domain.usecase.definition.GetCharactersUseCase
import com.danieh.kotlintestapp.extensions.getDisposableSingleObserver
import com.danieh.kotlintestapp.model.CharacterViewModel
import com.danieh.kotlintestapp.model.mapper.CharacterViewModelMapper
import com.danieh.kotlintestapp.ui.BasePresenter
import timber.log.Timber
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by danieh
 */
class MainPresenter
@Inject
constructor(private val view: MainView?, private val useCase: GetCharactersUseCase) : BasePresenter<MainView?>(WeakReference(view)) {

    private val mapper = CharacterViewModelMapper()

    private var page = 1
    private var characters = mutableListOf<CharacterViewModel>()

    fun getCharacters() {
        val disposable = useCase.execute(page)
                .doOnSubscribe {}
                .doFinally { view?.hideLoading() }
                .subscribeWith(getDisposableSingleObserver(
                        this@MainPresenter::onCharactersRetrieved,
                        this@MainPresenter::onCharactersFailed))
        disposables.add(disposable)
    }

    fun loadMore() {
        view?.showLoadingItem();
        getCharacters()
    }

    private fun onCharactersRetrieved(charactersConfigurationModel: CharactersConfigurationModel) {
        val charactersListView = mapper.map(charactersConfigurationModel.characters).toMutableList().apply {
            sortBy { it.name }
        }

        if (page == 1) {
            characters.addAll(charactersListView)
            view?.showCharacters(charactersListView)

        } else {

            for (character: CharacterViewModel in charactersListView) {
                if (!characters.contains(character)) {
                    characters.add(character)
                    view?.showNewCharacter(character)
                }
            }
        }

        if (charactersConfigurationModel.next == null) {
            view?.removeScrollListener()
        } else {
            view?.canLoadMore()
            page += 1
        }
    }

    private fun onCharactersFailed(it: Throwable) {
        Timber.d(it.message)
    }

    fun sortByName(characters: MutableList<CharacterViewModel>?) {
        characters?.let {
            it.sortBy { it.name }
            view?.updateCharacters(it)
        }
    }

    fun sortByBirthYear(characters: MutableList<CharacterViewModel>?) {
        characters?.let {
            it.sortBy { it.birth_year }
            view?.updateCharacters(it)
        }
    }

    fun favCharacter(characterViewModel: CharacterViewModel) {
        for (character: CharacterViewModel in characters) {
            if (character.id == characterViewModel.id) {
                character.saved = characterViewModel.saved
                return
            }
        }
    }

    fun clickCharacter(characterViewModel: CharacterViewModel) {
        // TODO
    }

    fun filterByName(query: String) {
        if (query.isEmpty()) {
            view?.updateCharacters(characters)
        } else {
            val filteredList = characters.filter { it.name.toLowerCase().contains(query.toLowerCase()) }
            view?.updateCharacters(filteredList.toMutableList())
        }
    }

    fun displayOnlyFavs(characters: MutableList<CharacterViewModel>?) {
        val filteredList = characters?.filter { it.saved } ?: mutableListOf()
        view?.updateCharacters(filteredList.toMutableList())
    }

    fun displayAll() {
        view?.updateCharacters(characters)
    }
}