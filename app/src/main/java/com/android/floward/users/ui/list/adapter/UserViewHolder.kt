package com.android.floward.users.ui.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.android.floward.R
import com.android.floward.arch.image.GlideApp
import com.android.floward.databinding.ItemUserBinding
import com.android.floward.users.ui.list.models.UserModel
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy

class UserViewHolder(
  private val binding: ItemUserBinding, private val onUserItemClick: (UserModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

  fun bind(userModel: UserModel) {
    itemView.setOnClickListener {
      onUserItemClick(userModel)
    }

    binding.tvUserName.text = userModel.name
    binding.tvPostCount.text = itemView.context.getString(R.string.posts, userModel.postsCount)

    GlideApp.with(itemView.context.applicationContext)
      .load(userModel.thumbnailUrl)
      .format(DecodeFormat.PREFER_RGB_565)
      .diskCacheStrategy(DiskCacheStrategy.ALL)
      .circleCrop()
      .into(binding.ivUserImage)
  }
}