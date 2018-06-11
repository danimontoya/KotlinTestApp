package com.danieh.domain.executor

import io.reactivex.Scheduler

/**
 * Created by danieh
 */
interface SchedulerProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
    fun computation(): Scheduler
}