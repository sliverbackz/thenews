package com.zmt.thenews.helper

sealed class State<out T> {
    open operator fun invoke(): T? = null
    class Loading<out T> : State<T>()
    data class Success<out T>(val value: T) : State<T>()
    data class Error<out T>(val exception: Throwable, val errorMessage: String,val value: T?) :
        State<T>()
}