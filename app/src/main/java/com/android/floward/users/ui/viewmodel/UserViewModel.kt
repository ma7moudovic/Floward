package com.android.floward.users.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.floward.posts.domain.GetPostsUseCase
import com.android.floward.users.domain.GetUsersUseCase
import com.android.floward.users.ui.models.UserModelMapper
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by shar2awy on 24/08/2022.
 */
class UserViewModel(
  private val getUsersUseCase: GetUsersUseCase,
  private val getPostsUseCase: GetPostsUseCase,
  private val mapper: UserModelMapper
) : ViewModel() {

  private val compositeDisposable: CompositeDisposable by lazy {
    CompositeDisposable()
  }

  private val _viewState: MutableLiveData<UsersViewState> by lazy {
    MutableLiveData()
  }

  val viewState: LiveData<UsersViewState> = _viewState

  fun getUsers() {
    compositeDisposable.add(getUsersUseCase().doOnSubscribe {
      _viewState.postValue(UsersViewState.Loading)
    }.zipWith(getPostsUseCase()) { users, posts ->
      val usersModels = users.map { user ->
        val postsCount = posts.filter { it.userId == user.id }.size
        mapper.map(user, postsCount)
      }
      usersModels
    }.subscribe({
      _viewState.postValue(UsersViewState.Data(it))
    }, {
      _viewState.postValue(UsersViewState.Error.UnknownError)
    })
    )
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.dispose()
  }
}