package com.android.floward.posts.domain

import com.android.floward.arch.network.DefaultApiClient
import com.android.floward.arch.scheduler.AppSchedulerProvider
import com.android.floward.arch.scheduler.SchedulerProvider
import com.android.floward.posts.data.DefaultPostRepo
import com.android.floward.posts.data.PostRemoteDataSource
import com.android.floward.posts.data.models.PostRemoteMapper

/**
 * Created by shar2awy on 24/08/2022.
 */
object GetPostsUseCaseProvider {

  private fun provideSchedulerProvider(): SchedulerProvider {
    return AppSchedulerProvider()
  }

  private fun providePostRepo(): DefaultPostRepo {
    return DefaultPostRepo(
      remoteDataSource = DefaultApiClient.getInstance()
        .getService(PostRemoteDataSource::class.java),
      mapper = PostRemoteMapper()
    )
  }

  fun provide(): GetPostsUseCase {
    return GetPostsUseCase(
      repo = providePostRepo(),
      schedulerProvider = provideSchedulerProvider()
    )
  }
}