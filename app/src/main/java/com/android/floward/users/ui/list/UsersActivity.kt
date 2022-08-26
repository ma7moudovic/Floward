package com.android.floward.users.ui.list

import android.os.Bundle
import android.view.View
import com.android.floward.arch.ui.BaseActivity
import com.android.floward.databinding.ActivityUsersBinding
import com.android.floward.users.ui.details.UserDetailsActivity
import com.android.floward.users.ui.list.adapter.UsersAdapter
import com.android.floward.users.ui.list.models.UserModel
import com.android.floward.users.ui.list.viewmodel.UsersListViewModel
import com.android.floward.users.ui.list.viewmodel.UsersListViewModelProvider
import com.android.floward.users.ui.list.viewmodel.UsersListViewState

class UsersActivity : BaseActivity() {

  private lateinit var binding: ActivityUsersBinding

  lateinit var viewModel: UsersListViewModel
  val adapter = UsersAdapter {
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

  private fun setViewState(viewState: UsersListViewState) {
    when (viewState) {
      UsersListViewState.Loading -> {
        binding.usersLoadingView.root.visibility = View.VISIBLE
        binding.usersDataView.visibility = View.GONE
      }
      is UsersListViewState.Data -> {
        binding.usersLoadingView.root.visibility = View.GONE
        binding.usersDataView.visibility = View.VISIBLE
        adapter.submitList(viewState.users)
      }
      is UsersListViewState.Error -> {}
    }
  }

  private fun initUI() {
    binding.usersDataView.adapter = adapter
  }

  private fun initViewModel() {
    viewModel = UsersListViewModelProvider.provide(this)
  }

  private fun openUserDetails(userModel: UserModel) {
    UserDetailsActivity.start(this, userId = userModel.id)
  }
}