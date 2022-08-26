package com.android.floward.users.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.floward.arch.image.GlideApp
import com.android.floward.arch.ui.BaseActivity
import com.android.floward.databinding.ActivityUserDetailsBinding
import com.android.floward.users.ui.details.adapter.PostsAdapter
import com.android.floward.users.ui.details.viewmodel.UserDetailsViewModel
import com.android.floward.users.ui.details.viewmodel.UserDetailsViewModelProvider
import com.android.floward.users.ui.details.viewmodel.UserDetailsViewState
import com.android.floward.users.ui.list.models.UserModel
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy

class UserDetailsActivity : BaseActivity() {

  private lateinit var binding: ActivityUserDetailsBinding

  lateinit var viewModel: UserDetailsViewModel
  val adapter = PostsAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityUserDetailsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    drawBehindStatusBar()
    setStatusBarStyle(true)

    val userId = intent.extras?.getInt(EXTRA_USER_ID) ?: 0

    initViewModel()
    initUI()
    initData(userId = userId)
  }

  private fun initData(userId: Int) {
    viewModel.viewState.observe(this, ::setViewState)
    viewModel.getUserPosts(userId = userId)
  }

  private fun setViewState(viewState: UserDetailsViewState) {
    when (viewState) {
      UserDetailsViewState.Loading -> {
        binding.postsLoadingView.root.visibility = View.VISIBLE
        binding.postsDataView.visibility = View.GONE
      }
      is UserDetailsViewState.Data -> {
        binding.postsLoadingView.root.visibility = View.GONE
        binding.postsDataView.visibility = View.VISIBLE

        setUserData(viewState.user)
      }
      is Error -> {}
    }
  }

  private fun initUI() {
    binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    binding.postsDataView.adapter = adapter
  }

  private fun initViewModel() {
    viewModel = UserDetailsViewModelProvider.provide(this)
  }

  private fun setUserData(userModel: UserModel){
    GlideApp.with(applicationContext)
      .load(userModel.imageUrl)
      .format(DecodeFormat.PREFER_RGB_565)
      .diskCacheStrategy(DiskCacheStrategy.ALL)
      .into(binding.ivUserImage)

    adapter.submitList(userModel.posts)
  }

  companion object {

    private const val EXTRA_USER_ID = "extra_user_id"

    @JvmStatic
    fun start(context: Context, userId: Int) {
      val starter = getIntent(context, userId)
      context.startActivity(starter)
    }

    @JvmStatic
    fun getIntent(context: Context, userId: Int): Intent {
      return Intent(context, UserDetailsActivity::class.java).putExtra(EXTRA_USER_ID, userId)
    }
  }
}