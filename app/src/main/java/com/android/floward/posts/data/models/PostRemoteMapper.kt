package com.android.floward.posts.data.models

import com.android.floward.posts.domain.models.Post

/**
 * Created by shar2awy on 24/08/2022.
 */
class PostRemoteMapper {
  fun map(postRemote: PostRemote): Post {
    return Post(
      id = postRemote.id,
      userId = postRemote.userId,
      title = postRemote.title,
      body = postRemote.body
    )
  }

  fun map(posts: List<PostRemote>): List<Post> {
    return posts.map {
      map(it)
    }
  }
}