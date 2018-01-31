package com.acv.marvel.app

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.acv.marvel.R

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

typealias Obs<T> = ((T) -> Unit)
typealias Obs2<T> = (Obs<T>) -> Unit

infix fun <M, T : M> Fragment.observe(f: () -> LiveData<T>): Obs2<T> =
        { o: (T) -> Unit -> f().observe(this, Observer { o(it!!) }) }

infix fun <T> Obs2<T>.`do`(f: Obs<T>) =
        this({ f(it) })

inline fun <reified T : ViewModel> AppCompatActivity.viewModelProviders(): T =
        ViewModelProviders.of(this).get(T::class.java)

inline fun <reified T : ViewModel> Fragment.viewModelProviders(): T =
        ViewModelProviders.of(this).get(T::class.java)
