package com.android.floward.posts.domain

import com.android.floward.arch.scheduler.SchedulerProvider
import com.android.floward.posts.domain.models.Post
import io.reactivex.Single

/**
 * Created by shar2awy on 24/08/2022.
 */
class GetPostsUseCase(
  private val repo: PostRepo,
  private val schedulerProvider: SchedulerProvider
) {
  operator fun invoke(): Single<List<Post>> {
    return repo.getPosts()
      .subscribeOn(schedulerProvider.io())
      .observeOn(schedulerProvider.ui())
  }
}