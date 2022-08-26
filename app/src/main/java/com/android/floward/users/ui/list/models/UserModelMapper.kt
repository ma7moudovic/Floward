package com.android.floward.users.ui.list.models

import com.android.floward.posts.domain.models.Post
import com.android.floward.users.domain.models.User

/**
 * Created by shar2awy on 26/08/2022.
 */
class UserModelMapper {

  fun map(user: User, postsCount: Int): UserModel {
    return UserModel(
      id = user.id,
      name = user.name,
      imageUrl = user.imageUrl,
      thumbnailUrl = user.thumbnailUrl,
      postsCount = postsCount
    )
  }

  fun map(user: User, posts: List<Post>): UserModel {
    return UserModel(
      id = user.id,
      name = user.name,
      imageUrl = user.imageUrl,
      thumbnailUrl = user.thumbnailUrl,
      posts = posts
    )
  }
}
