package com.zmt.thenews.di

import com.zmt.thenews.BuildConfig
import com.zmt.thenews.model.remote.interceptors.AuthHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val CONNECT_TIMEOUT = 15L
    private const val READ_TIMEOUT = 60L
    private const val WRITE_TIMEOUT = 15L

    @Provides
    @Singleton
    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggerInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggerInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggerInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return loggerInterceptor
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggerInterceptor: HttpLoggingInterceptor, authInterceptor: AuthHeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggerInterceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}