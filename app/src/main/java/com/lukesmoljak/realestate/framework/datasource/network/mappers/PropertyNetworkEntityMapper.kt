package com.lukesmoljak.realestate.framework.datasource.network.mappers

import com.lukesmoljak.realestate.business.domain.model.Property
import com.lukesmoljak.realestate.framework.datasource.network.api.response.PropertyListingItemResponse

class PropertyNetworkEntityMapper {

    private fun mapFromEntity(entity: PropertyListingItemResponse): Property {
        val addressSplitComma = entity.location.address.split(",")
        val addressSplitSpace = addressSplitComma[1].split(" ")

        return Property(
            id = entity.id,
            numBedrooms = entity.bedrooms,
            numBathrooms = entity.bathrooms,
            numCarSpaces = entity.carSpaces,
            streetAddress = addressSplitComma[0],
            suburb = entity.location.suburb,
            postcode = addressSplitSpace[addressSplitSpace.count()-1],
            propertyImageUrl = entity.propertyImages[0].attachment.url,
            agentName = entity.agent.firstName + " " + entity.agent.lastName,
            agentAvatarUrl = entity.agent.avatar.small.url
        )
    }

    fun entityListToDomainList(entityList: ArrayList<PropertyListingItemResponse>): List<Property> {
        val result = ArrayList<Property>()
        for (entity in entityList) {
            result.add(mapFromEntity(entity))
        }
        return result
    }
}