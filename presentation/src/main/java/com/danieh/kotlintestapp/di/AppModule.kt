package com.danieh.kotlintestapp.di

import android.content.Context
import com.danieh.domain.executor.SchedulerProvider
import com.danieh.domain.executor.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by danieh
 */
@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    internal fun context(): Context {
        return context
    }

    @Singleton
    @Provides
    internal fun schedulerProvider(): SchedulerProvider {
        return SchedulerProviderImpl()
    }

}