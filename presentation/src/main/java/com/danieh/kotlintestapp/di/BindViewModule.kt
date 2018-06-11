package com.danieh.kotlintestapp.di

import com.danieh.kotlintestapp.ui.main.MainActivity
import com.danieh.kotlintestapp.ui.main.MainView
import dagger.Binds
import dagger.Module

/**
 * Created by danieh
 */
@Module
abstract class BindViewModule {

    @Binds
    abstract fun bindMainView(mainActivity: MainActivity): MainView
}