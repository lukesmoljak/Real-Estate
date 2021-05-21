package com.lukesmoljak.realestate.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
object BuildVariantModule {

    @Provides
    fun provideOkHttpLoggingInterceptor(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.NONE)
            )
    }
}