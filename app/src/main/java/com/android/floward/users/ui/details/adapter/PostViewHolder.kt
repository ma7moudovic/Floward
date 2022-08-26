package com.android.floward.users.ui.details.adapter

import androidx.recyclerview.widget.RecyclerView
import com.android.floward.databinding.ItemPostBinding
import com.android.floward.posts.domain.models.Post

class PostViewHolder(
  private val binding: ItemPostBinding
) : RecyclerView.ViewHolder(binding.root) {

  fun bind(post: Post) {
    binding.tvPostTitle.text = post.title
    binding.tvPostBody.text = post.body

  }
}