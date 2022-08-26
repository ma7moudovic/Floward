package com.android.floward.users.ui.details.viewmodel

import com.android.floward.users.ui.list.models.UserModel

/**
 * Created by shar2awy on 24/08/2022.
 */
sealed class UserDetailsViewState {
  object Loading : UserDetailsViewState()

  data class Data(val user: UserModel) : UserDetailsViewState()

  sealed class Error : UserDetailsViewState() {
    object NetworkError : Error()
    object UnknownError : Error()
  }
}
