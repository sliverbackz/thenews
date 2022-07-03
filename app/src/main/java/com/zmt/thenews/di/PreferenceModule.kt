package com.zmt.thenews.di

import com.zmt.thenews.model.remote.interceptors.auth.AuthTokenStore
import com.zmt.thenews.model.remote.interceptors.auth.AuthTokenStoreImpl
import com.zmt.thenews.model.remote.interceptors.auth.AuthTokenStoreSecuredImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {
    @Binds
    @NormalPref
    abstract fun bindAuthTokenStorage(authTokenStoreImpl: AuthTokenStoreImpl): AuthTokenStore

    @Binds
    @SecuredPref
    abstract fun bindAuthTokenStoreSecured(authTokenStoreSecuredImpl: AuthTokenStoreSecuredImpl): AuthTokenStore
}