package com.android.floward.users.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android.floward.R
import com.android.floward.databinding.ItemUserBinding
import com.android.floward.users.ui.models.UserModel

class UsersAdapter(
  private val onUserItemClick: (UserModel) -> Unit
) : ListAdapter<UserModel, UserViewHolder>(UsersAdapterDiffUtil) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    return UserViewHolder(ItemUserBinding.bind(view), onUserItemClick)
  }

  override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
    holder.bind(userModel = getItem(position))
  }
}