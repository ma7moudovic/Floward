package com.android.floward.users.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.floward.users.domain.GetUsersUseCase
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by shar2awy on 24/08/2022.
 */
class UserViewModel(val getUsersUseCase: GetUsersUseCase) : ViewModel() {

  private val compositeDisposable: CompositeDisposable by lazy {
    CompositeDisposable()
  }

  private val _viewState: MutableLiveData<UsersViewState> by lazy {
    MutableLiveData()
  }

  val viewState: LiveData<UsersViewState> = _viewState

  fun getUsers() {
    compositeDisposable.add(getUsersUseCase()
      .doOnSubscribe {
        _viewState.postValue(UsersViewState.Loading)
      }
      .subscribe({
        _viewState.postValue(UsersViewState.Data(it))
      }, {
        _viewState.postValue(UsersViewState.Error.UnknownError)

      }
      )
    )
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.dispose()
  }
}