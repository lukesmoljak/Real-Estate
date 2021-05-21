package com.lukesmoljak.realestate.business.interactors

import com.lukesmoljak.realestate.business.data.network.NetworkDataSource
import com.lukesmoljak.realestate.business.data.network.NetworkErrors
import com.lukesmoljak.realestate.business.domain.model.Property
import com.lukesmoljak.realestate.business.domain.state.DataState
import com.lukesmoljak.realestate.framework.datasource.network.mappers.PropertyNetworkEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetPropertyListingsUseCase(
    private val networkDataSource: NetworkDataSource,
    private val propertyNetworkEntityMapper: PropertyNetworkEntityMapper
) {

    fun getPropertyListings(): Flow<DataState<List<Property>>> = flow {
        try {
            emit(DataState.loading())
            val apiResponse = networkDataSource.getPropertyListings()
            val listings = propertyNetworkEntityMapper.entityListToDomainList(apiResponse.data)
            emit(DataState.success(listings))
        } catch (e: Exception) {
            emit(DataState.error<List<Property>>(e.message ?: NetworkErrors.UNKNOWN_ERROR))
        }
    }
}