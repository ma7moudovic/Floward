package com.android.floward.users.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.floward.arch.ui.BaseActivity
import com.android.floward.databinding.ActivityUsersBinding
import com.android.floward.users.ui.adapter.UsersAdapter
import com.android.floward.users.ui.models.UserModel
import com.android.floward.users.ui.viewmodel.UserViewModel
import com.android.floward.users.ui.viewmodel.UsersViewModelProvider
import com.android.floward.users.ui.viewmodel.UsersViewState
import com.android.floward.users.ui.viewmodel.UsersViewState.Data
import com.android.floward.users.ui.viewmodel.UsersViewState.Loading

class UsersActivity : BaseActivity() {

  private lateinit var binding: ActivityUsersBinding

  lateinit var viewModel: UserViewModel
  val adapter = UsersAdapter{
    openUserDetails(it)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityUsersBinding.inflate(layoutInflater)
    setContentView(binding.root)
    drawBehindStatusBar()
    setStatusBarStyle(true)

    initViewModel()
    initUI()
    initData()
  }

  private fun initData() {
    viewModel.viewState.observe(this, ::setViewState)
    viewModel.getUsers()
  }

  private fun setViewState(viewState: UsersViewState) {
    when (viewState) {
      Loading -> {
        binding.usersLoadingView.root.visibility = View.VISIBLE
        binding.usersDataView.visibility = View.GONE
      }
      is Data -> {
        binding.usersLoadingView.root.visibility = View.GONE
        binding.usersDataView.visibility = View.VISIBLE
        adapter.submitList(viewState.users)
      }
      is Error -> {}
    }
  }

  private fun initUI() {
    binding.usersDataView.adapter = adapter
  }

  private fun initViewModel() {
    viewModel = UsersViewModelProvider.provide(this)
  }

  private fun openUserDetails(userModel: UserModel) {

  }
}