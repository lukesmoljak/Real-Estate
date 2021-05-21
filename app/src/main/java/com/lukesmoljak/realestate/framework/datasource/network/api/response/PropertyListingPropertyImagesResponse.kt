package com.lukesmoljak.realestate.framework.datasource.network.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PropertyListingPropertyImagesResponse(

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("attachment")
    @Expose
    var attachment: PropertyListingAttachmentResponse,
)