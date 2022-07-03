package com.zmt.thenews.model.remote.interceptors.auth

import com.zmt.thenews.model.remote.interceptors.Token

interface AuthTokenStore {

    fun storeToken(token: Token)

    fun retrieveToken(): Token

}
