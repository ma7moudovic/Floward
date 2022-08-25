package com.android.floward.users.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.android.floward.databinding.ItemUserBinding
import com.android.floward.users.ui.models.UserModel

class UserViewHolder(
  private val binding: ItemUserBinding, private val onUserItemClick: (UserModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

  fun bind(userModel: UserModel) {
    itemView.setOnClickListener {
      onUserItemClick(userModel)
    }

    binding.tvUserName.text = userModel.name
    binding.tvPostCount.text = "14 posts"

  //        GlideApp.with(itemView.context.applicationContext)
    //            .load(chapterData.model.chapterLogo)
    //            .placeholder(R.drawable.ic_chapter_placeholder)
    //            .into(binding.ivChapterImage)
  }
}