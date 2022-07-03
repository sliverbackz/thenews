package com.zmt.thenews.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zmt.thenews.databinding.ActivityNewsSearchBinding
import com.zmt.thenews.viewmodel.NewsSearchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsSearchActivity : AppCompatActivity() {

    private var _binding: ActivityNewsSearchBinding? = null

    private val binding: ActivityNewsSearchBinding get() = _binding!!

    private val viewModel: NewsSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }
}