package com.danieh.kotlintestapp

import android.os.StrictMode
import com.danieh.kotlintestapp.di.AppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

/**
 * Created by danieh
 */
class KotlinApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return AppComponent.create(this, "http://swapi.co/api/people/", true)
    }

    companion object {
        lateinit var instance: KotlinApplication
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        initialize()
    }

    private fun initialize() {
        if (BuildConfig.DEBUG) {

            Timber.plant(Timber.DebugTree())

//            // https://developer.android.com/reference/android/os/StrictMode.html
//            StrictMode.enableDefaults()
//
//            // http://facebook.github.io/stetho/
//            Stetho.initializeWithDefaults(this)

            // https://github.com/square/leakcanary
//            if (LeakCanary.isInAnalyzerProcess(this)) {
//                return
//            }
//            LeakCanary.install(this)
        }
    }

    fun appComponent(): AppComponent {
        return appComponent
    }
}