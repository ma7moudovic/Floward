package com.android.floward.arch.ui

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

  fun drawBehindStatusBar() {
    window.decorView.systemUiVisibility =
      window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    window.statusBarColor = resources.getColor(android.R.color.transparent, theme)
  }

  fun setStatusBarStyle(isLight: Boolean) {
    if (isLight) window.decorView.systemUiVisibility =
      window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    else window.decorView.systemUiVisibility =
      window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
  }
}