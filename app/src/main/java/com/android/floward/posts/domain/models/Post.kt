package com.android.floward.posts.domain.models

import com.squareup.moshi.JsonClass

/**
 * Created by shar2awy on 26/08/2022.
 */
data class Post(
  val id: Int,
  val userId: Int,
  val title: String,
  val body: String
)
