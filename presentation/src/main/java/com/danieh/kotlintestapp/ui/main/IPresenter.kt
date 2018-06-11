package com.danieh.kotlintestapp.ui.main

import android.os.Bundle

/**
 * Created by danieh
 */
interface IPresenter {
    
    /**
     * On of callback for the activity or fragment lifecycle.

     * @param savedInstanceState The bundle with the previous state.
     * *
     * @param extras The bundle.
     */
    fun onCreate(savedInstanceState: Bundle?, extras: Bundle?)

    /**
     * Callback that ensures the global state of the application is ready. This ensures that if there
     * is something asynchronous to be loaded it is done in the proper time.
     */
    fun onReady()

    /**
     * On start equivalent callback for an activity or a fragment.
     */
    fun onStart()

    /**
     * On pause equivalent callback for an activity or a fragment.
     */
    fun onPause()

    /**
     * On restore lifecycle callback for an activity or a fragment.
     */
    fun onResume()

    /**
     * On stop equivalent callback for an activity or a fragment.
     */
    fun onStop()

    /**
     * On destroy lifecycle callback for an activity or a fragment.
     */
    fun onDestroy()
}