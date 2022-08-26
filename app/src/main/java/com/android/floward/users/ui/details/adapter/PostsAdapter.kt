package com.android.floward.users.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android.floward.R
import com.android.floward.databinding.ItemPostBinding
import com.android.floward.posts.domain.models.Post

class PostsAdapter : ListAdapter<Post, PostViewHolder>(PostsAdapterDiffUtil) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
    return PostViewHolder(ItemPostBinding.bind(view))
  }

  override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
    holder.bind(post = getItem(position))
  }
}