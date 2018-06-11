package com.danieh.kotlintestapp.ui

import android.content.Context
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import com.danieh.kotlintestapp.ui.main.IPresenter
import dagger.android.support.AndroidSupportInjection

/**
 * Created by danieh
 */
abstract class BaseFragment<out P : IPresenter> : Fragment() {

    protected abstract val presenter: P

    @CallSuper
    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
