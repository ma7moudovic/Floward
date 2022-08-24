package com.android.floward.users.data

import com.android.floward.users.data.models.UserRemoteMapper
import com.android.floward.users.domain.UserRepo
import com.android.floward.users.domain.models.User
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import java.io.IOException

/**
 * Created by shar2awy on 24/08/2022.
 */
class DefaultUserRepo(
  private val remoteDataSource: UserRemoteDataSource,
  private val mapper: UserRemoteMapper
) : UserRepo {
  override fun getUsers(): Single<List<User>> {
    return remoteDataSource.getUsers()
      .doOnSuccess {
        handleException(it)
      }
      .map { it.response()?.body() }
      .map { data ->
        data?.let(mapper::map)
      }
  }

  private fun handleException(result: Result<*>) {
    if (result.isError) {
      if (result.error() is IOException) throw(Exception("Network Error!"))
      else throw(Exception(
        result.error()?.message
      ))
    }
  }
}