package com.android.floward.users.data.models

import com.android.floward.users.domain.models.User

/**
 * Created by shar2awy on 24/08/2022.
 */
class UserRemoteMapper {
  fun map(userRemote: UserRemote): User {
    return User(
      id = userRemote.userId,
      name = userRemote.name,
      imageUrl = userRemote.url,
      thumbnailUrl = userRemote.thumbnailUrl
    )
  }

  fun map(users: List<UserRemote>): List<User> {
    return users.map {
      map(it)
    }
  }
}