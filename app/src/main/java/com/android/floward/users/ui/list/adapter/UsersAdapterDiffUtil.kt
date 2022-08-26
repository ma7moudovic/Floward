package com.android.floward.users.ui.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.android.floward.users.ui.list.models.UserModel

/**
 * Created by shar2awy on 26/08/2022.
 */
object UsersAdapterDiffUtil : DiffUtil.ItemCallback<UserModel>() {
  override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
    return oldItem == newItem
  }
}