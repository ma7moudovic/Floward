package com.android.floward.posts.data

import com.android.floward.posts.data.models.PostRemoteMapper
import com.android.floward.posts.domain.PostRepo
import com.android.floward.posts.domain.models.Post
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import java.io.IOException

/**
 * Created by shar2awy on 24/08/2022.
 */
class DefaultPostRepo(
  private val remoteDataSource: PostRemoteDataSource, private val mapper: PostRemoteMapper
) : PostRepo {
  override fun getPosts(): Single<List<Post>> {
    return remoteDataSource.getPosts()
      .doOnSuccess {
        handleException(it)
      }.map { it.response()?.body() }
      .map { data ->
        mapper.map(data)
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