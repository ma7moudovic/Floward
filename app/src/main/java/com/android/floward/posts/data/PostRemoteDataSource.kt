package com.android.floward.posts.data

import com.android.floward.posts.data.models.PostRemote
import com.android.floward.users.data.models.UserRemote
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET

/**
 * Created by shar2awy on 24/08/2022.
 */
interface PostRemoteDataSource {
  @GET("posts")
  fun getPosts(): Single<Result<List<PostRemote>>>
}