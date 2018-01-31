package com.acv.marvel.app

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
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

inline fun <reified T : Fragment> createFragment(vararg args: Pair<String, String>): T =
        with(T::class.java.newInstance()) {
            val bundle = Bundle()
            args.map { bundle.putString(it.first, it.second) }
            arguments = bundle
            return this
        }


inline fun <reified T : ViewModel> FragmentActivity.viewModelProviders(): T =
        ViewModelProviders.of(this).get(T::class.java)

infix fun ViewGroup.inflate(res: Int) =
        LayoutInflater.from(context).inflate(res, this, false)
