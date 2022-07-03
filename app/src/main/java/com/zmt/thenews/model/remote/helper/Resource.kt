package com.zmt.thenews.model.remote.helper

sealed class Resource<out T> {

    data class Success<out T>(val data: T) : Resource<T>()

    data class Error<out T>(val message: String, val throwable: Throwable) : Resource<T>()

    data class Loading<out T>(val isLoading: Boolean = true) : Resource<T>()


}
