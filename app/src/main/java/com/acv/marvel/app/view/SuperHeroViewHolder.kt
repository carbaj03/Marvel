package com.acv.marvel.app.view

import android.view.View
import com.acv.marvel.app.load
import com.acv.marvel.presentation.SuperHeroView
import kotlinx.android.synthetic.main.item_superhero.view.*

class SuperHeroViewHolder(view: View) : ViewHolder<SuperHeroView>(view) {

    override fun bind(model: SuperHeroView) = with(itemView) {
        with(model) {
            tvName.text = name
            ivIcon.load(model.photo)
        }
    }
}