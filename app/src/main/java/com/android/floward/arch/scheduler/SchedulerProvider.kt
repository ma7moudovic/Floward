package com.android.floward.arch.scheduler

import io.reactivex.Scheduler

/**
 * Created by shar2awy on 24/08/2022.
 */
interface SchedulerProvider {
  fun io(): Scheduler
  fun ui(): Scheduler
}