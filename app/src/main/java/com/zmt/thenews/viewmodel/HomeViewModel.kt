package com.zmt.thenews.viewmodel

import androidx.lifecycle.ViewModel
import com.zmt.thenews.helper.State
import com.zmt.thenews.repository.NewsSourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsSourceRepository: NewsSourceRepository
) : ViewModel() {
    suspend fun fetchAllNewSources() {
        withContext(Dispatchers.IO) {
            newsSourceRepository.fetchAllSources().collect {
                if (it is State.Success) {
                    Timber.i("Source size %d", it.value.size)
                }
            }
        }
    }

    suspend fun getAllNewSources() {
        withContext(Dispatchers.IO) {
            newsSourceRepository.getAllSources().collect {
                Timber.i("Source db size %d", it?.size)
            }
        }
    }
}