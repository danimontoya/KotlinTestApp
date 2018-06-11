package com.danieh.kotlintestapp.di


import com.danieh.kotlintestapp.ui.main.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by danieh
 */
@Module
abstract class AndroidInjectorModule {

    @ContributesAndroidInjector(modules = arrayOf(BindViewModule::class))
    abstract fun injectMainActivity(): MainActivity
}
