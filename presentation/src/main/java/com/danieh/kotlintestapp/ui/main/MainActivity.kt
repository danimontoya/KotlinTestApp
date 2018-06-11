package com.danieh.kotlintestapp.ui.main

import android.app.SearchManager
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import com.danieh.kotlintestapp.R
import com.danieh.kotlintestapp.databinding.ActivityMainBinding
import com.danieh.kotlintestapp.model.CharacterViewModel
import com.danieh.kotlintestapp.ui.BaseActivity
import com.danieh.kotlintestapp.ui.main.adapter.CharacterLayoutManager
import com.danieh.kotlintestapp.ui.main.adapter.CharactersAdapter
import com.danieh.kotlintestapp.ui.main.adapter.DrawableDividerItemDecorator
import javax.inject.Inject


class MainActivity : BaseActivity<MainPresenter>(), MainView, SearchView.OnQueryTextListener, View.OnFocusChangeListener {

    companion object {
        val ITEMS_BEFORE_RELOAD = 3
    }

    @Inject
    override lateinit var presenter: MainPresenter

    private lateinit var binding: ActivityMainBinding

    private lateinit var searchView: SearchView

    private var adapter: CharactersAdapter? = null

    private lateinit var scrollListener: RecyclerView.OnScrollListener

    private var loadingNewItems = false

    private var searchingItems = false

    private var showingFavs = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // action bar
        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.screen_title)
        actionBar.subtitle = getString(R.string.screen_subtitle)
        actionBar.elevation = 4.0F

        presenter.getCharacters()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.characters_menu, menu)
        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(this@MainActivity)
            setOnQueryTextFocusChangeListener(this@MainActivity)
        }
        val hintTextId = searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
        searchView.findViewById<TextView>(hintTextId).apply {
            hint = getString(R.string.menu_search_hint)
            setHintTextColor(Color.WHITE)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort_name -> {
                presenter.sortByName(adapter?.characters)
                return true
            }
            R.id.action_sort_birth_year -> {
                presenter.sortByBirthYear(adapter?.characters)
                return true
            }
            R.id.action_favorites -> {
                if (!showingFavs) {
                    showingFavs = true
                    presenter.displayOnlyFavs(adapter?.characters)
                    item.setTitle(getString(R.string.menu_show_all))
                } else {
                    showingFavs = false
                    presenter.displayAll()
                    item.setTitle(getString(R.string.menu_favorites))
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showCharacters(@NonNull characters: MutableList<CharacterViewModel>) {
        adapter = CharactersAdapter(this, characters.toMutableList(), object : CharactersAdapter.CharacterItemListener {
            override fun onFavCharacter(characterViewModel: CharacterViewModel) {
                presenter.favCharacter(characterViewModel)
            }

            override fun onClickCharacter(characterViewModel: CharacterViewModel) {
                presenter.clickCharacter(characterViewModel)
            }

        }).apply {
            onAttachedToRecyclerView(binding.recyclerview)
            setHasStableIds(true)
        }
        binding.recyclerview.let {
            it.adapter = adapter
            it.addItemDecoration(DrawableDividerItemDecorator(this, DrawableDividerItemDecorator.Orientation.HORIZONTAL)
                    .showFirst(false)
                    .showLast(false))
            it.layoutManager = CharacterLayoutManager(this)
        }
        scrollListener = object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                Log.d("ApiConnection", "ITEMS_BEFORE_RELOAD=" + ITEMS_BEFORE_RELOAD + ", ItemCount=" + recyclerView.adapter.itemCount +
                        ", lastVisibleItemPosition=" + lastVisibleItemPosition)
                val isTimeToLoadMoreItems = ITEMS_BEFORE_RELOAD >= recyclerView.adapter.itemCount - lastVisibleItemPosition
                if (!loadingNewItems && isTimeToLoadMoreItems && !searchingItems && !showingFavs) {
                    loadingNewItems = true
                    presenter.loadMore()
                }
            }
        }
        binding.recyclerview.addOnScrollListener(scrollListener)
    }

    override fun removeScrollListener() {
        binding.recyclerview.removeOnScrollListener(scrollListener)
    }

    override fun canLoadMore() {
        loadingNewItems = false
    }

    override fun showLoadingItem() {
        adapter?.addLoadView()
    }

    override fun hideLoadingItem() {
        adapter?.removeLoadView()
    }

    override fun showNewCharacter(newCharacter: CharacterViewModel) {
        adapter?.addCharacter(newCharacter)
    }

    override fun updateCharacters(characters: MutableList<CharacterViewModel>) {
        adapter?.let {
            it.characters = characters
            it.notifyDataSetChanged()
        }
        binding.recyclerview.postDelayed(Runnable { binding.recyclerview.smoothScrollToPosition(0) }, 200)
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (v?.id == searchView.id)
            searchingItems = hasFocus
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            presenter.filterByName(query)
        } ?: presenter.filterByName("")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            presenter.filterByName(newText)
        } ?: presenter.filterByName("")
        return true
    }
}
