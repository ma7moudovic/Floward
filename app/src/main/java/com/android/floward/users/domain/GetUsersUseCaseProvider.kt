package com.android.floward.users.domain

import com.android.floward.arch.network.DefaultApiClient
import com.android.floward.arch.scheduler.AppSchedulerProvider
import com.android.floward.arch.scheduler.SchedulerProvider
import com.android.floward.users.data.DefaultUserRepo
import com.android.floward.users.data.UserRemoteDataSource
import com.android.floward.users.data.models.UserRemoteMapper

/**
 * Created by shar2awy on 24/08/2022.
 */
object GetUsersUseCaseProvider {

  private fun provideSchedulerProvider(): SchedulerProvider {
    return AppSchedulerProvider()
  }

  private fun provideUsersRepo(): DefaultUserRepo {
    return DefaultUserRepo(
      remoteDataSource = DefaultApiClient.getInstance()
        .getService(UserRemoteDataSource::class.java),
      mapper = UserRemoteMapper()
    )
  }

  fun provide(): GetUsersUseCase {
    return GetUsersUseCase(
      repo = provideUsersRepo(),
      schedulerProvider = provideSchedulerProvider()
    )
  }
}