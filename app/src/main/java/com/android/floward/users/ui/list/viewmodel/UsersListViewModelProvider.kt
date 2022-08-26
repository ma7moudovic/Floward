package com.android.floward.users.ui.list.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.floward.posts.domain.GetPostsUseCaseProvider
import com.android.floward.users.domain.GetUsersUseCaseProvider
import com.android.floward.users.ui.list.models.UserModelMapper

/**
 * Created by shar2awy on 24/08/2022.
 */
object UsersListViewModelProvider {
  fun provide(activity: AppCompatActivity): UsersListViewModel {
    val factory = UsersListViewModelFactory(
      getUsersUseCase = GetUsersUseCaseProvider.provide(),
      getPostsUseCase = GetPostsUseCaseProvider.provide(),
      mapper = UserModelMapper()
    )
    return ViewModelProvider(activity, factory)[UsersListViewModel::class.java]
  }
}