package com.android.floward.users.data.models

import com.squareup.moshi.JsonClass

/**
 * Created by shar2awy on 24/08/2022.
 */

@JsonClass(generateAdapter = true)
data class UserRemote(
  val userId: Int,
  val albumId: Int,
  val name: String,
  val url: String,
  val thumbnailUrl: String
)
