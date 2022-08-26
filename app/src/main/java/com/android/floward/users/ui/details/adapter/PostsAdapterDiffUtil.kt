package com.android.floward.users.ui.details.adapter

import androidx.recyclerview.widget.DiffUtil
import com.android.floward.posts.domain.models.Post
import com.android.floward.users.ui.list.models.UserModel

/**
 * Created by shar2awy on 26/08/2022.
 */
object PostsAdapterDiffUtil : DiffUtil.ItemCallback<Post>() {
  override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
    return oldItem == newItem
  }
}