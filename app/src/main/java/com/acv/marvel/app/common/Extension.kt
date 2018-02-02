package com.acv.marvel.app.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.acv.marvel.R
import com.acv.marvel.app.view.common.CircleTransform
import com.squareup.picasso.Picasso

inline fun <reified T : Fragment> AppCompatActivity.load(vararg args: Pair<String, String>) =
        with(supportFragmentManager) {
            beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.container, createFragment<T>(*args), T::class.java.simpleName)
                    .commit()
        }

inline fun <reified T : Activity> Fragment.load(vararg pairs: Pair<String, String>) =
        with(activity!!) {
            goToActivity<T>(*pairs)
            setFadeInOutAnimation()
        }

fun Activity.setFadeInOutAnimation() = overridePendingTransition(R.anim.fade_in, R.anim.fade_out)


inline fun <reified T : Activity> Activity.goToActivity(vararg pairs: Pair<String, String>) {
    val intent = Intent(this, T::class.java)
    pairs.map { intent.putExtra(it.first, it.second) }
    startActivity(intent)
}


inline fun <reified T : Fragment> createFragment(vararg args: Pair<String, String>): T =
        with(T::class.java.newInstance()) {
            val bundle = Bundle()
            args.map { bundle.putString(it.first, it.second) }
            arguments = bundle
            return this
        }


infix fun ViewGroup.inflate(res: Int) =
        LayoutInflater.from(context).inflate(res, this, false)


fun ImageView.loadCircle(path: String) =
        Picasso.with(context).load(path).transform(CircleTransform()).error(R.drawable.ic_android_black_24dp).into(this)


fun View.gone() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun Context.color(color: Int) =
        ContextCompat.getColor(this, color)
