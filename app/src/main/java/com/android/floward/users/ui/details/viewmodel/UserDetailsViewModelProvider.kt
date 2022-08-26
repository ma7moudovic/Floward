package com.android.floward.users.ui.details.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.floward.posts.domain.GetPostsUseCaseProvider
import com.android.floward.users.domain.GetUsersUseCaseProvider
import com.android.floward.users.ui.list.models.UserModelMapper

/**
 * Created by shar2awy on 24/08/2022.
 */
object UserDetailsViewModelProvider {
  fun provide(activity: AppCompatActivity): UserDetailsViewModel {
    val factory = UserDetailsViewModelFactory(
      getUsersUseCase = GetUsersUseCaseProvider.provide(),
      getPostsUseCase = GetPostsUseCaseProvider.provide(),
      mapper = UserModelMapper()
    )
    return ViewModelProvider(activity, factory)[UserDetailsViewModel::class.java]
  }
}