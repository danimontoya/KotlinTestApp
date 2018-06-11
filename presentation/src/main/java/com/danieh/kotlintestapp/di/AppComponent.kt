package com.danieh.kotlintestapp.di

import android.content.Context
import com.danieh.domain.executor.SchedulerProvider
import com.danieh.kotlintestapp.KotlinApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by danieh
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class, UseCaseModule::class, AndroidInjectorModule::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<KotlinApplication> {

    fun context(): Context
    fun scheduler(): SchedulerProvider

    companion object {
        fun create(context: Context, url: String, debug: Boolean): AppComponent {
            return DaggerAppComponent.builder()
                    .appModule(AppModule(context))
                    .networkModule(NetworkModule(context, url, debug))
                    .dataModule(DataModule())
                    .useCaseModule(UseCaseModule())
                    .build()
        }
    }

//    fun postExecutionThread(): PostExecutionThread
//
//    fun repoRepository(): RepoRepository
//
//    fun realm(): Realm
}