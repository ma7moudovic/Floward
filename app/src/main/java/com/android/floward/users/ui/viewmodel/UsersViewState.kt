package com.android.floward.users.ui.viewmodel

import com.android.floward.users.ui.models.UserModel

/**
 * Created by shar2awy on 24/08/2022.
 */
sealed class UsersViewState {
  object Loading : UsersViewState()

  data class Data(val users: List<UserModel>) : UsersViewState()

  sealed class Error : UsersViewState() {
    object NetworkError : Error()
    object UnknownError : Error()
  }
}
