package com.zmt.thenews.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zmt.thenews.databinding.FragmentAllNewsBinding
import com.zmt.thenews.viewmodel.TopHeadlinesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllNewsFragment : Fragment() {

    private var _binding: FragmentAllNewsBinding? = null

    private val binding: FragmentAllNewsBinding get() = _binding!!

    private val viewModel: TopHeadlinesViewModel by viewModels()

    companion object {
        fun newInstance() = AllNewsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllNewsBinding.inflate(inflater, container, false)
        return binding.root
    }
}