package com.android.floward.posts.data.models

import com.squareup.moshi.JsonClass

/**
 * Created by shar2awy on 26/08/2022.
 */
@JsonClass(generateAdapter = true)
data class PostRemote(
  val id: Int,
  val userId: Int,
  val title: String,
  val body: String
)
