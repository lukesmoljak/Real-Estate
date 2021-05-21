package com.lukesmoljak.realestate.business.data.network

import com.lukesmoljak.realestate.business.domain.model.Property
import com.lukesmoljak.realestate.framework.datasource.network.api.response.PropertyListingResponse

interface NetworkDataSource {
    suspend fun getPropertyListings(): PropertyListingResponse
}