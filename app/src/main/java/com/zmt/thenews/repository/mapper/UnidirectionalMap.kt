package com.zmt.thenews.repository.mapper

interface UnidirectionalMap<F, T> {
    fun map(item: F): T
}
