package com.lukesmoljak.realestate.business.interactors

import com.google.gson.GsonBuilder
import com.lukesmoljak.realestate.business.data.network.MockWebServerResponses
import com.lukesmoljak.realestate.business.data.network.NetworkDataSourceImpl
import com.lukesmoljak.realestate.business.domain.model.Property
import com.lukesmoljak.realestate.framework.datasource.network.api.ApiService
import com.lukesmoljak.realestate.framework.datasource.network.mappers.PropertyNetworkEntityMapper
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection


/*

    USE CASES:
    1. Successful download, loading true emitted, data emitted, loading false emitted
    2. failure URL incorrect, loading true emitted, error emitted, loading false emitted

*/

class GetPropertyListingsUseCaseTests {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var apiService: ApiService
    private lateinit var sut: GetPropertyListingsUseCase

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        val baseUrl = mockWebServer.url("/")
        val gson = GsonBuilder()
                .setLenient()
                .create()
        apiService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                    GsonConverterFactory.create(
                            gson
                    )
            )
            .build()
            .create(ApiService::class.java)
        sut = GetPropertyListingsUseCase(NetworkDataSourceImpl(
                apiService),
                PropertyNetworkEntityMapper()
        )
    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getPropertyListingsUseCase_success_verifyEmittedDataAndLoadingDataState() {
        mockWebServer.enqueue(
                MockResponse()
                        .setResponseCode(HttpURLConnection.HTTP_OK)
                        .setBody(MockWebServerResponses.PROPERTIES_RESPONSE)
        )

        runBlocking {
            val flowItems = sut.getPropertyListings().toList()

            // First item should be loading
            assert(flowItems[0].loading)

            // Second item should be the list data
            val listingResult = flowItems[1].data
            assertTrue(listingResult!!.isNotEmpty())

            // Confirm one of the items match
            val expectedItem1 = Property(
                    id = 2,
                    numBedrooms = 2,
                    numBathrooms = 2,
                    numCarSpaces = 1,
                    streetAddress = "7/236 Pacific Highway",
                    suburb = "Crows Nest",
                    postcode = "2065",
                    propertyImageUrl = "https://images.pexels.com/photos/2119713/pexels-photo-2119713.jpeg",
                    agentName = "Stephen Gilmore",
                    agentAvatarUrl = "https://images.pexels.com/photos/936043/pexels-photo-936043.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            )

            assertEquals(expectedItem1, listingResult[1])

            // Loading should be turned off
            assertFalse(flowItems[1].loading)
        }
    }

    @Test
    fun getPropertyListingsUseCase_failureHTTPError_verifyError() {
        mockWebServer.enqueue(
                MockResponse()
                        .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                        .setBody("{}")
        )

        runBlocking {
            val flowItems = sut.getPropertyListings().toList()

            // First item should be loading
            assert(flowItems[0].loading)

            // No data should be returned
            assertEquals(null, flowItems[1].data)

            // Error should be emitted
            val error = flowItems[1].error
            assertTrue(error != null)

            // Loading should be turned off
            assertFalse(flowItems[1].loading)
        }
    }

}