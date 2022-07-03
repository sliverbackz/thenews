package com.zmt.thenews.model.remote.interceptors

import com.zmt.thenews.BuildConfig
import com.zmt.thenews.di.SecuredPref
import com.zmt.thenews.model.remote.interceptors.auth.AuthTokenStore
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class AuthHeaderInterceptor @Inject constructor(
    @SecuredPref
    private val authStore: AuthTokenStore
) : Interceptor {
    init {
        authStore.storeToken(Token(BuildConfig.API_KEY))
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = authStore.retrieveToken().value ?: return chain.proceed(chain.request())
        val requestBuilder = chain.request().newBuilder()
        Timber.i(authStore.retrieveToken().value)
        requestBuilder.addHeader("Authorization", "Bearer $token")
        val newRequest = requestBuilder.build()
        return chain.proceed(newRequest)
    }
}

