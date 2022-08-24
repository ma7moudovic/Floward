package com.android.floward.users.ui.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.floward.users.domain.GetUsersUseCaseProvider

/**
 * Created by shar2awy on 24/08/2022.
 */
object UsersViewModelProvider {
  fun provide(activity: AppCompatActivity): UserViewModel {
    val factory = UsersViewModelFactory(
      getUsersUseCase = GetUsersUseCaseProvider.provide()
    )
    return ViewModelProvider(activity, factory)[UserViewModel::class.java]
  }
}