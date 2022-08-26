package com.android.floward.users.ui.list.models

import com.android.floward.posts.domain.models.Post

/**
 * Created by shar2awy on 26/08/2022.
 */
data class UserModel(
  val id: Int,
  val name: String,
  val imageUrl: String,
  val thumbnailUrl: String,
  val postsCount: Int = 0,
  val posts: List<Post> = listOf()
)
