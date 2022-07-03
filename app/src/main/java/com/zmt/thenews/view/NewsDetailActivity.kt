package com.zmt.thenews.view

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import com.zmt.thenews.R
import com.zmt.thenews.databinding.ActivityNewsDetailBinding
import com.zmt.thenews.extension.gone
import com.zmt.thenews.extension.show
import com.zmt.thenews.helper.AsyncViewResource
import com.zmt.thenews.helper.NetworkUtils
import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews
import com.zmt.thenews.viewmodel.NewsDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsDetailActivity : AppCompatActivity() {

    private var _binding: ActivityNewsDetailBinding? = null

    private val binding: ActivityNewsDetailBinding get() = _binding!!

    private val viewModel: NewsDetailViewModel by viewModels()

    private val args: NewsDetailActivityArgs by navArgs()

    private var news: TopHeadlinesNews? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val title = args.title
        viewModel.getHeadlinesNewDetail(title)
        binding.webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            loadWithOverviewMode = true
            useWideViewPort = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
        }


        /**Handle back action inside web view**/
        binding.webView.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                val webView = v as WebView
                when (keyCode) {
                    KeyEvent.KEYCODE_BACK -> if (webView.canGoBack()) {
                        webView.goBack()
                        return@setOnKeyListener true
                    }
                }
            }
            return@setOnKeyListener false
        }

        viewModel.newsLiveData.observe(this) {
            when (it) {
                is AsyncViewResource.Success -> {
                    supportActionBar?.title = it.value?.title
                    binding.webView.loadUrl(it.value?.url ?: "")
                    binding.webView.show()
                    binding.emptyView.root.gone()
                    news = it.value
                    if (it.value?.isSaved == true) {
                        binding.fabFav.imageTintList =
                            ColorStateList.valueOf(this.getColor(R.color.white))
                    } else {
                        binding.fabFav.imageTintList =
                            ColorStateList.valueOf(this.getColor(android.R.color.darker_gray))
                    }

                    if (!NetworkUtils.isInternetAvailable(this)) {
                        binding.webView.gone()
                        binding.emptyView.root.show()
                        binding.emptyView.tvEmptyText.setText(R.string.connection_error)
                    }

                }
                is AsyncViewResource.Error -> {
                    binding.webView.gone()
                    binding.emptyView.root.show()
                    Toast.makeText(this, it.exception.message, Toast.LENGTH_SHORT).show()
                }
                else -> {

                }
            }
        }
        binding.fabFav.setOnClickListener {
            news?.apply {
                viewModel.updateIsSaved(this.title)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}