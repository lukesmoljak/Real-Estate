package com.lukesmoljak.realestate.business.data.network

import com.lukesmoljak.realestate.framework.datasource.network.api.ApiService
import com.lukesmoljak.realestate.framework.datasource.network.api.response.PropertyListingResponse

class NetworkDataSourceImpl(
    private val apiService: ApiService
): NetworkDataSource {
    override suspend fun getPropertyListings(): PropertyListingResponse {
        return apiService.getPropertyListings()
    }
}