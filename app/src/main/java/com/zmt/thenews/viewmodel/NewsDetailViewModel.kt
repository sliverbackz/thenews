package com.zmt.thenews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zmt.thenews.helper.AsyncViewStateLiveData
import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews
import com.zmt.thenews.repository.TopHeadlinesNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val topHeadlinesNewsRepository: TopHeadlinesNewsRepository
) : ViewModel() {

    val newsLiveData = AsyncViewStateLiveData<TopHeadlinesNews?>()

    fun getHeadlinesNewDetail(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            topHeadlinesNewsRepository.getByTitle(title).collect {
                if (it == null) {
                    newsLiveData.postError(Throwable("News not found!"))
                } else {
                    newsLiveData.postSuccess(it)
                }
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

    suspend fun isSaved(title: String) = topHeadlinesNewsRepository.isSaved(title)
}