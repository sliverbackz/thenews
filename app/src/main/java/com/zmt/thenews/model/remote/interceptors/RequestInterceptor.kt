package com.zmt.thenews.model.remote.interceptors

import com.zmt.thenews.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor() : Interceptor {
    companion object{
        private const val API_KEY_NAME = "api_key"
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val url = originalUrl.newBuilder()
            .addQueryParameter(API_KEY_NAME, BuildConfig.API_KEY)
            .build()
        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}