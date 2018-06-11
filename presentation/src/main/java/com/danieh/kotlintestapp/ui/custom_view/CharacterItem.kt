package com.danieh.kotlintestapp.ui.custom_view

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.danieh.kotlintestapp.R
import com.danieh.kotlintestapp.databinding.ViewCharacterItemBinding
import com.danieh.kotlintestapp.model.CharacterViewModel
import com.danieh.kotlintestapp.ui.main.adapter.CharactersAdapter

/**
 * Created by danieh
 */
class CharacterItem @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), CharacterItemView {

    private var presenter: CharacterItemPresenter
    private var binding: ViewCharacterItemBinding

    lateinit var listener: CharactersAdapter.CharacterItemListener

    init {
        presenter = CharacterItemPresenter(this)
        binding = DataBindingUtil.inflate<ViewCharacterItemBinding>(LayoutInflater.from(context), R.layout.view_character_item, this, true)
        binding.favoriteImageview.setOnClickListener { presenter.favoriteClick() }
        binding.root.setOnClickListener { presenter.rootClick() }
    }

    fun bindModel(characterViewModel: CharacterViewModel) {
        presenter.bindModel(characterViewModel)
    }

    override fun displayName(name: String) {
        binding.nameTextview.text = name
    }

    override fun displayFav(saved: Boolean) {
        val resource = if (saved) R.mipmap.icon_heart_enabled else R.mipmap.icon_heart_disabled
        binding.favoriteImageview.setImageResource(resource)
    }

    override fun notifyFavClicked(model: CharacterViewModel) {
        listener.onFavCharacter(model)
    }

    override fun notifyRootClicked(model: CharacterViewModel) {
        listener.onClickCharacter(model)
    }
}