package com.android.floward.users.ui.models

import com.android.floward.users.domain.models.User

/**
 * Created by shar2awy on 26/08/2022.
 */
class UserModelMapper {

  fun map(user: User): UserModel {
    return UserModel(
      id = user.id,
      name = user.name,
      imageUrl = user.imageUrl,
      thumbnailUrl = user.thumbnailUrl
    )
  }

  fun map(users: List<User>): List<UserModel> {
    return users.map {
      map(it)
    }
  }
}
