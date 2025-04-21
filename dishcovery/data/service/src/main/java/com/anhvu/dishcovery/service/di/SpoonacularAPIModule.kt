package com.anhvu.dishcovery.service.di

import com.anhvu.dishcovery.service.spoonacular.SpoonacularAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SpoonacularAPIModule {
    @Singleton
    @Provides
    fun provideSpooncularAPI(retrofit: Retrofit): SpoonacularAPI {
        return retrofit.create(SpoonacularAPI::class.java)
    }
}