package com.zmt.thenews.viewmodel

import androidx.lifecycle.ViewModel
import com.zmt.thenews.repository.AllNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsSearchViewModel @Inject constructor(
    private val allNewsRepository: AllNewsRepository
) : ViewModel() {

}