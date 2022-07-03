package com.zmt.thenews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zmt.thenews.helper.AsyncViewStateLiveData
import com.zmt.thenews.helper.State
import com.zmt.thenews.helper.UiState
import com.zmt.thenews.model.Params
import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews
import com.zmt.thenews.repository.TopHeadlinesNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesViewModel @Inject constructor(
    private val topHeadlinesNewsRepository: TopHeadlinesNewsRepository
) : ViewModel() {

    val newsStateLiveData = AsyncViewStateLiveData<List<TopHeadlinesNews>?>()

    fun getTopHeadlinesByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            topHeadlinesNewsRepository.getTopHeadlinesByCategory(category).collect {
                when (it) {
                    is State.Error -> {
                        if (it.value.isNullOrEmpty()) {
                            newsStateLiveData.postError(it.exception, it.errorMessage)
                        } else {
                           // newsStateLiveData.postSuccess(it.value)
                        }
                    }
                    is State.Loading -> {
                        newsStateLiveData.postLoading()
                    }
                    is State.Success -> {
                       // newsStateLiveData.postSuccess(it.value)
                    }
                }
            }

            topHeadlinesNewsRepository.getTopHeadlinesByCategoryFlow(category).collect{
                newsStateLiveData.postSuccess(it)
            }
        }
    }

    fun updateIsSaved(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val isSaved = topHeadlinesNewsRepository.isSaved(title)
            val save = isSaved ?: true
            topHeadlinesNewsRepository.updateIsSaved(!save, title)
        }
    }
}