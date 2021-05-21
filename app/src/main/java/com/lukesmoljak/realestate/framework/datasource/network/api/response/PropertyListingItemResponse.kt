package com.lukesmoljak.realestate.framework.datasource.network.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PropertyListingItemResponse(

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("bedrooms")
    @Expose
    var bedrooms: Int,

    @SerializedName("bathrooms")
    @Expose
    var bathrooms: Int,

    @SerializedName("carspaces")
    @Expose
    var carSpaces: Int,

    @SerializedName("location")
    @Expose
    var location: PropertyListingLocationResponse,

    @SerializedName("property_images")
    @Expose
    var propertyImages: Array<PropertyListingPropertyImagesResponse>,

    @SerializedName("agent")
    @Expose
    var agent: PropertyListingAgentResponse,
)