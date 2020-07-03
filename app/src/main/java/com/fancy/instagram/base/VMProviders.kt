package com.fancy.instagram.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.fancy.instagram.application.InstagramApp

object VMProviders {

    private val factory = BaseViewModelFactory()

    fun of(activity: AppCompatActivity): ViewModelProvider = ViewModelProvider(activity, factory)

    fun of(activity: FragmentActivity): ViewModelProvider = ViewModelProvider(activity, factory)

    fun of(fragment: Fragment): ViewModelProvider = ViewModelProvider(fragment, factory)
}
