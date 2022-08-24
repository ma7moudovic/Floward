package com.android.floward.users.domain

import com.android.floward.users.domain.models.User
import io.reactivex.Single

/**
 * Created by shar2awy on 24/08/2022.
 */
interface UserRepo {
  fun getUsers(): Single<List<User>>
}