package com.acv.marvel.app.view

import android.view.View
import android.widget.TextView
import com.acv.marvel.R
import com.acv.marvel.presentation.SuperHero

class SuperHeroViewHolder(view: View) : ViewHolder<SuperHero>(view) {
    var tvName: TextView = view.findViewById(R.id.tvName)

    override fun bind(model: SuperHero) =
            with(model) {
                tvName.text = name
            }
}