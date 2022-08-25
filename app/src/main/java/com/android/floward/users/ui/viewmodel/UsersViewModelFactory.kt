package com.android.floward.users.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.floward.users.domain.GetUsersUseCase
import com.android.floward.users.ui.models.UserModelMapper

class UsersViewModelFactory(
  private val getUsersUseCase: GetUsersUseCase,
  private val mapper: UserModelMapper
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return UserViewModel(
      getUsersUseCase = getUsersUseCase,
      mapper = mapper
    ) as T
  }
}