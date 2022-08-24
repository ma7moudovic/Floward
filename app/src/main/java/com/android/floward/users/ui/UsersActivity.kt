package com.android.floward.users.ui

import android.os.Bundle
import android.widget.Toast
import com.android.floward.R.layout
import com.android.floward.arch.ui.BaseActivity
import com.android.floward.users.ui.viewmodel.UserViewModel
import com.android.floward.users.ui.viewmodel.UsersViewModelProvider
import com.android.floward.users.ui.viewmodel.UsersViewState
import com.android.floward.users.ui.viewmodel.UsersViewState.Data
import com.android.floward.users.ui.viewmodel.UsersViewState.Loading

class UsersActivity : BaseActivity() {
  lateinit var viewModel: UserViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
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
      Loading -> {}
      is Data -> {
        Toast.makeText(this, "users count ${viewState.users.size}", Toast.LENGTH_SHORT).show()
      }
      is Error -> {}
    }
  }

  private fun initUI() {

  }

  private fun initViewModel() {
    viewModel = UsersViewModelProvider.provide(this)
  }
}