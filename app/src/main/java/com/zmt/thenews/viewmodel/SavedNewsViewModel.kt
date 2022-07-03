package com.zmt.thenews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zmt.thenews.helper.AsyncViewStateLiveData
import com.zmt.thenews.model.local.db.news.entity.TopHeadlinesNews
import com.zmt.thenews.repository.TopHeadlinesNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val topHeadlinesNewsRepository: TopHeadlinesNewsRepository
) : ViewModel() {

    val newsStateLiveData = AsyncViewStateLiveData<List<TopHeadlinesNews>?>()

    fun getSavedNews() {
        viewModelScope.launch(Dispatchers.IO) {
            topHeadlinesNewsRepository.getAllSavedNews().collect {
                if(it.isNullOrEmpty()){
                    newsStateLiveData.postError(Exception("You have no saved news!"))
                }else{
                    newsStateLiveData.postSuccess(it)
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
}