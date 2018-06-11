package com.danieh.kotlintestapp.ui.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.NO_ID
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danieh.kotlintestapp.R
import com.danieh.kotlintestapp.model.CharacterViewModel
import kotlinx.android.synthetic.main.list_character_item.view.*

/**
 * Created by danieh
 */
class CharactersAdapter(val context: Context, var characters: MutableList<CharacterViewModel>, val characterItemListener: CharacterItemListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val CHARACTER_ITEM = 0
        const val LOADING_ITEM = 1
    }

    private lateinit var recyclerView: RecyclerView

    private var loadingItem = 0

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CHARACTER_ITEM -> CharacterViewHolder(inflater.inflate(R.layout.list_character_item, parent, false))
            LOADING_ITEM -> LoadingViewHolder(inflater.inflate(R.layout.list_loading_item, parent, false))
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount() = characters.size + loadingItem

    override fun getItemViewType(position: Int): Int {
        return if (position < characters.size)
            CHARACTER_ITEM
        else {
            LOADING_ITEM
        }
    }

    override fun getItemId(position: Int): Long {
        return when (getItemViewType(position)) {
            CHARACTER_ITEM -> characters.get(position).id
            else -> NO_ID
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterViewHolder -> holder.bind(characters[position], characterItemListener)
            is LoadingViewHolder -> {
            }
            else -> throw IllegalArgumentException()
        }

    }

    inner class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(characterViewModel: CharacterViewModel, characterItemListener: CharacterItemListener) {
            itemView.character_item?.bindModel(characterViewModel)
            itemView.character_item?.listener = characterItemListener
        }
    }

    inner class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)

    fun addLoadView() {
        if (loadingItem == 1) return
        // Show loading view
        loadingItem = 1
        recyclerView.post { notifyItemInserted(itemCount - 1) }
    }

    fun removeLoadView() {
        if (loadingItem == 0) return
        // hide loading view
        loadingItem = 0
        recyclerView.post { notifyItemRemoved(itemCount - 1) }
    }

    fun addCharacter(newCharacter: CharacterViewModel) {
        // hide loading view
        loadingItem = 0
        characters.add(newCharacter)
        recyclerView.post { notifyItemInserted(itemCount - 1) }
    }

    interface CharacterItemListener {
        fun onFavCharacter(characterViewModel: CharacterViewModel)
        fun onClickCharacter(characterViewModel: CharacterViewModel)
    }
}