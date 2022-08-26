package com.android.floward.users.ui.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.floward.posts.domain.GetPostsUseCase
import com.android.floward.users.domain.GetUsersUseCase
import com.android.floward.users.ui.list.models.UserModelMapper

class UserDetailsViewModelFactory(
  private val getUsersUseCase: GetUsersUseCase,
  private val getPostsUseCase: GetPostsUseCase,
  private val mapper: UserModelMapper
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return UserDetailsViewModel(
      getUsersUseCase = getUsersUseCase,
      getPostsUseCase = getPostsUseCase,
      mapper = mapper
    ) as T
  }
}