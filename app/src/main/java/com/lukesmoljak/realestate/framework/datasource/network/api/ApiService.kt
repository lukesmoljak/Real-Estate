package com.lukesmoljak.realestate.framework.datasource.network.api

import com.lukesmoljak.realestate.framework.datasource.network.api.response.PropertyListingResponse
import retrofit2.http.GET

interface ApiService {

    @GET("properties")
    suspend fun getPropertyListings(): PropertyListingResponse
}