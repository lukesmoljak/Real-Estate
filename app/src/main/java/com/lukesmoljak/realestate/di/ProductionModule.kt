package com.lukesmoljak.realestate.di

import com.google.gson.Gson
import com.lukesmoljak.realestate.business.data.network.NetworkConstants.BASE_API_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object ProductionModule {

    @Provides
    fun provideRetrofitBuilder(
        gsonBuilder: Gson,
        builder: OkHttpClient.Builder
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .client(builder.build())
    }
}