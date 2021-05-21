package com.lukesmoljak.realestate.business.domain.model

data class Property(
    val id: Int,
    val numBedrooms: Int,
    val numBathrooms: Int,
    val numCarSpaces: Int,
    val streetAddress: String,
    val suburb: String,
    val postcode: String,
    val propertyImageUrl: String,
    val agentName: String,
    val agentAvatarUrl: String
)