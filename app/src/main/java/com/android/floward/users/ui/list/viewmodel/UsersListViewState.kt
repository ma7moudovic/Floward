package com.android.floward.users.ui.list.viewmodel

import com.android.floward.users.ui.list.models.UserModel

/**
 * Created by shar2awy on 24/08/2022.
 */
sealed class UsersListViewState {
  object Loading : UsersListViewState()

  data class Data(val users: List<UserModel>) : UsersListViewState()

  sealed class Error : UsersListViewState() {
    object NetworkError : Error()
    object UnknownError : Error()
  }
}
