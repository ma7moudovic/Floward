package com.android.floward.users.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.floward.users.domain.GetUsersUseCase

class UsersViewModelFactory(private val getUsersUseCase: GetUsersUseCase) :
  ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return UserViewModel(getUsersUseCase = getUsersUseCase) as T
  }
}