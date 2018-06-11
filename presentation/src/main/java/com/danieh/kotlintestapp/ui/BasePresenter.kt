package com.danieh.kotlintestapp.ui

import android.os.Bundle
import com.danieh.kotlintestapp.ui.main.IPresenter
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by danieh
 */
abstract class BasePresenter<V>(private val view: WeakReference<V>) : IPresenter {

    /**
     * Provides the view.
     * @return The current view.
     */
    fun view(): V? = view.get()

    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?, extras: Bundle?) {}

    override fun onReady() {}

    override fun onStart() {}

    override fun onResume() {}

    override fun onPause() {}

    override fun onStop() {
        disposables.clear()
    }

    override fun onDestroy() {}
}