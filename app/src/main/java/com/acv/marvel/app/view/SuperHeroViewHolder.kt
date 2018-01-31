package com.acv.marvel.app.view

import android.view.View
import android.widget.TextView
import com.acv.marvel.R

class SuperHeroViewHolder(view: View) : ViewHolder<SuperHero>(view) {
    var tvName: TextView = view.findViewById(R.id.tvName)

    override fun bind(model: SuperHero) =
            with(model) {
                tvName.text = name
            }
}

data class SuperHero(
        val id: String,
        val name: String,
        val photo: String? = null,
        val isAvenger: Boolean,
        val description: String
) : ItemVisitable {
    override fun type() = R.layout.item_skill
}


