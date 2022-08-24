package com.android.floward.users.domain

import com.android.floward.arch.scheduler.SchedulerProvider
import com.android.floward.users.domain.models.User
import io.reactivex.Single

/**
 * Created by shar2awy on 24/08/2022.
 */
class GetUsersUseCase(
  private val repo: UserRepo,
  private val schedulerProvider: SchedulerProvider
) {
  operator fun invoke():Single<List<User>>{
    return repo.getUsers()
      .subscribeOn(schedulerProvider.io())
      .observeOn(schedulerProvider.ui())
  }
}