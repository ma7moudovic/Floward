package com.android.floward.posts.domain

import com.android.floward.posts.domain.models.Post
import io.reactivex.Single

/**
 * Created by shar2awy on 24/08/2022.
 */
interface PostRepo {
  fun getPosts(): Single<List<Post>>
}