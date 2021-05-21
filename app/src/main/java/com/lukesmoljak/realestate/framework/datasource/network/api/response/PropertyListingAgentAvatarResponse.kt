package com.lukesmoljak.realestate.framework.datasource.network.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PropertyListingAgentAvatarResponse(

    @SerializedName("small")
    @Expose
    var small: PropertyListingAgentAvatarSmallResponse,
)