package com.zmt.thenews.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zmt.thenews.databinding.FragmentSavedNewsBinding
import com.zmt.thenews.extension.gone
import com.zmt.thenews.extension.show
import com.zmt.thenews.helper.AsyncViewResource
import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews
import com.zmt.thenews.view.adapter.ClickEvent
import com.zmt.thenews.view.adapter.TopHeadlinesNewsAdapter
import com.zmt.thenews.viewmodel.SavedNewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment : Fragment() {

    private var _binding: FragmentSavedNewsBinding? = null

    private val binding: FragmentSavedNewsBinding get() = _binding!!

    private val viewModel: SavedNewsViewModel by viewModels()

    companion object {
        fun newInstance() = SavedNewsFragment()
    }

    private val adapter: TopHeadlinesNewsAdapter by lazy {
        TopHeadlinesNewsAdapter(object : ClickEvent {
            override fun itemClick(item: TopHeadlinesNews) {
                val action =
                    SavedNewsFragmentDirections.actionSavedNewsFragmentToNewsDetailActivity(
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
        binding.rvNews.adapter = adapter
        viewModel.getSavedNews()
        viewModel.newsStateLiveData.observe(viewLifecycleOwner) {
            if (it is AsyncViewResource.Success) {
                binding.emptyView.root.gone()
                binding.rvNews.show()
                adapter.submitList(it.value)
            } else if (it is AsyncViewResource.Error) {
                binding.rvNews.gone()
                binding.emptyView.tvEmptyText.text = it.exception.message
                binding.emptyView.root.show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }
}