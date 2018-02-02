package com.acv.marvel.app.view.marvel

import android.view.View
import com.acv.marvel.app.common.loadCircle
import com.acv.marvel.app.view.ViewHolder
import com.acv.marvel.presentation.SuperHeroView
import kotlinx.android.synthetic.main.item_superhero.view.*

class MarvelViewHolder(view: View) : ViewHolder<SuperHeroView>(view) {

    override fun bind(model: SuperHeroView) = with(itemView) {
        with(model) {
            tvName.text = name
            tvRealName.text = realName
            tvHeight.text = height
            ivIcon.loadCircle(model.photo)
        }
    }
}