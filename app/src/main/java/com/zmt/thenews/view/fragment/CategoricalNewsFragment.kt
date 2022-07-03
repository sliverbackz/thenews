package com.zmt.thenews.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.zmt.thenews.databinding.FragmentCategoricalNewsBinding
import com.zmt.thenews.view.adapter.CategoricalNewsPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

class CategoricalNewsFragment : Fragment() {

    private var _binding: FragmentCategoricalNewsBinding? = null

    private val binding: FragmentCategoricalNewsBinding get() = _binding!!

    companion object {
        fun newInstance() = CategoricalNewsFragment()
    }

    private val categoryList = listOf(
        Pair("us", "U.S."),
        Pair("business", "Business"),
        Pair("entertainment", "Entertainment"),
        Pair("health", "Health"),
        Pair("science", "Science"),
        Pair("sports", "Sports"),
        Pair("technology", "Technology"),
        Pair("general", "General")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoricalNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPagerNews.adapter = CategoricalNewsPagerAdapter(requireActivity(), categoryList)
        TabLayoutMediator(binding.tabLayoutNews, binding.viewPagerNews) { tab, position ->
            tab.text = categoryList[position].second
        }.attach()
    }
}