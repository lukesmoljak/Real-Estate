package com.lukesmoljak.realestate.framework.datasource.network.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PropertyListingAgentAvatarSmallResponse(

    @SerializedName("url")
    @Expose
    var url: String,
)