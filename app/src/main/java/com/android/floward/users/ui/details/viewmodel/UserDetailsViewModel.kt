package com.android.floward.users.ui.details.viewmodel

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
class UserDetailsViewModel(
  private val getUsersUseCase: GetUsersUseCase,
  private val getPostsUseCase: GetPostsUseCase,
  private val mapper: UserModelMapper
) : ViewModel() {

  private val compositeDisposable: CompositeDisposable by lazy {
    CompositeDisposable()
  }

  private val _viewState: MutableLiveData<UserDetailsViewState> by lazy {
    MutableLiveData()
  }

  val viewState: LiveData<UserDetailsViewState> = _viewState

  fun getUserPosts(userId: Int) {
    compositeDisposable.add(getUsersUseCase().doOnSubscribe {
      _viewState.postValue(UserDetailsViewState.Loading)
    }.zipWith(getPostsUseCase()) { users, posts ->
      val user = users.first { it.id == userId }
      val userPosts = posts.filter { it.userId == user.id }
      mapper.map(user, userPosts)
    }.subscribe({
      _viewState.postValue(UserDetailsViewState.Data(it))
    }, {
      _viewState.postValue(UserDetailsViewState.Error.UnknownError)
    })
    )
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.dispose()
  }
}