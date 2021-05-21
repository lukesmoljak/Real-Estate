package com.lukesmoljak.realestate.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lukesmoljak.realestate.business.data.network.NetworkDataSource
import com.lukesmoljak.realestate.business.data.network.NetworkDataSourceImpl
import com.lukesmoljak.realestate.business.interactors.GetPropertyListingsUseCase
import com.lukesmoljak.realestate.framework.datasource.network.api.ApiService
import com.lukesmoljak.realestate.framework.datasource.network.mappers.PropertyNetworkEntityMapper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object AppModule {

    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    fun provideApiService(
        retrofitBuilder: Retrofit.Builder
    ): ApiService {
        return retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun providePropertyNetworkEntityMapper(): PropertyNetworkEntityMapper {
        return PropertyNetworkEntityMapper()
    }

    @Provides
    fun provideNetworkDataSource(
        apiService: ApiService
    ): NetworkDataSource {
        return NetworkDataSourceImpl(
            apiService = apiService
        )
    }

    @Provides
    fun provideGetPropertyListingsUseCase(
        networkDataSource: NetworkDataSource,
        propertyNetworkEntityMapper: PropertyNetworkEntityMapper
    ): GetPropertyListingsUseCase {
        return GetPropertyListingsUseCase(
            networkDataSource = networkDataSource,
            propertyNetworkEntityMapper = propertyNetworkEntityMapper
        )
    }
}