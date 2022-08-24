package com.android.floward.users.ui

import android.os.Bundle
import com.android.floward.R.layout
import com.android.floward.arch.network.DefaultApiClient
import com.android.floward.arch.ui.BaseActivity

class UsersActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
    drawBehindStatusBar()
    setStatusBarStyle(true)

  }
}