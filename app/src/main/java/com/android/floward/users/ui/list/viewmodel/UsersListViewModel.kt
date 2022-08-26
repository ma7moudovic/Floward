package com.android.floward.users.ui.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.floward.posts.domain.GetPostsUseCase
import com.android.floward.users.domain.GetUsersUseCase
import com.android.floward.users.ui.list.models.UserModelMapper
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by shar2awy on 24/08/2022.
 */
class UsersListViewModel(
  private val getUsersUseCase: GetUsersUseCase,
  private val getPostsUseCase: GetPostsUseCase,
  private val mapper: UserModelMapper
) : ViewModel() {

  private val compositeDisposable: CompositeDisposable by lazy {
    CompositeDisposable()
  }

  private val _viewState: MutableLiveData<UsersListViewState> by lazy {
    MutableLiveData()
  }

  val viewState: LiveData<UsersListViewState> = _viewState

  fun getUsers() {
    compositeDisposable.add(getUsersUseCase().doOnSubscribe {
      _viewState.postValue(UsersListViewState.Loading)
    }.zipWith(getPostsUseCase()) { users, posts ->
      val usersModels = users.map { user ->
        val postsCount = posts.filter { it.userId == user.id }.size
        mapper.map(user, postsCount)
      }
      usersModels
    }.subscribe({
      _viewState.postValue(UsersListViewState.Data(it))
    }, {
      _viewState.postValue(UsersListViewState.Error.UnknownError)
    })
    )
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.dispose()
  }
}