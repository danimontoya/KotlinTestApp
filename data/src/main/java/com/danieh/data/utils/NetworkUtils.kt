package com.danieh.data.utils

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import java.util.ArrayList

/**
 * Created by danieh
 */
@SuppressLint("MissingPermission")
object NetworkUtils {

    private var connectivityManager: ConnectivityManager? = null
    private val listeners = ArrayList<NetworkAvailableListener>()
    private var isTesting: Boolean = false
    private var testingValue: Boolean = true

    var isNetworkAvailable: Boolean = false
        get() {
            return if(isTesting) {
                testingValue
            } else {
                if (connectivityManager == null) {
                    throw RuntimeException("Network util not initialised")
                }
                val activeNetworkInfo = connectivityManager?.activeNetworkInfo

                activeNetworkInfo != null && activeNetworkInfo.isConnected
            }
        }

    fun init(context: Context, isTesting: Boolean = false, testingValue: Boolean = true) {
        this.isTesting = isTesting
        this.testingValue = testingValue
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val receiver = object : BroadcastReceiver() {
            private var lastIsNetworkAvailable = isNetworkAvailable

            override fun onReceive(context: Context, intent: Intent) {
                val isNetworkAvailable = isNetworkAvailable
                if (lastIsNetworkAvailable != isNetworkAvailable) {
                    lastIsNetworkAvailable = isNetworkAvailable
                    fireListeners(isNetworkAvailable)
                }
            }
        }

        context.registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    private fun fireListeners(isNetworkAvailable: Boolean) {
        var listeners: Array<NetworkAvailableListener>? = null

        synchronized(NetworkUtils.listeners) {
            listeners = NetworkUtils.listeners.toTypedArray()
        }

        listeners?.let {
            for (listener in it) {
                if (isNetworkAvailable) {
                    listener.onNetworkAvailable()
                } else {
                    listener.onNetworkUnavailable()
                }
            }
        }
    }

    fun addListener(listener: NetworkAvailableListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: NetworkAvailableListener) {
        listeners.remove(listener)
    }

    fun removeAllListeners() {
        listeners.clear()
    }

    interface NetworkAvailableListener {
        fun onNetworkAvailable() = Unit
        fun onNetworkUnavailable() = Unit
    }
}
