package com.zmt.thenews.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zmt.thenews.databinding.FragmentTopHeadlinesBinding
import com.zmt.thenews.helper.AsyncViewResource
import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews
import com.zmt.thenews.view.adapter.ClickEvent
import com.zmt.thenews.view.adapter.TopHeadlinesNewsAdapter
import com.zmt.thenews.viewmodel.TopHeadlinesViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TopHeadlinesFragment : Fragment() {

    private var _binding: FragmentTopHeadlinesBinding? = null

    private val binding: FragmentTopHeadlinesBinding get() = _binding!!

    private val viewModel: TopHeadlinesViewModel by viewModels()


    companion object {
        var cate: String = ""
        fun newInstance(category: String): TopHeadlinesFragment {
            val frag = TopHeadlinesFragment()
            this.cate = category
            return frag
        }
    }

    private val adapter: TopHeadlinesNewsAdapter by lazy {
        TopHeadlinesNewsAdapter(object : ClickEvent {
            override fun itemClick(item: TopHeadlinesNews) {
                val action =
                    CategoricalNewsFragmentDirections.actionCategoricalNewsFragmentToNewsDetailActivity(
                        item.title, false
                    )
                findNavController().navigate(action)
            }

            override fun favClick(item: TopHeadlinesNews) {
                viewModel.updateIsSaved(item.title)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopHeadlinesByCategory(cate)
        binding.rvNews.adapter = adapter
        viewModel.newsStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is AsyncViewResource.Error -> {
                    val show = adapter.itemCount > 0
                    binding.rvNews.isVisible = show
                    binding.loadingView.progressBar.isVisible = false
                    binding.emptyView.tvEmptyText.isVisible = !show
                }
                is AsyncViewResource.Success -> {
                    binding.rvNews.isVisible = true
                    binding.loadingView.progressBar.isVisible = false
                    adapter.submitList(it.value)
                    binding.emptyView.tvEmptyText.isVisible = it.value.isNullOrEmpty()
                }
                else -> {
                    val show = adapter.itemCount > 0
                    binding.rvNews.isVisible = show
                    binding.loadingView.progressBar.isVisible = !show
                    binding.emptyView.tvEmptyText.isVisible = !show
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopHeadlinesBinding.inflate(inflater, container, false)
        return binding.root
    }
}