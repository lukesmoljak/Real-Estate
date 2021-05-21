package com.lukesmoljak.realestate.framework.datasource.network.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PropertyListingAgentResponse(

    @SerializedName("first_name")
    @Expose
    var firstName: String,

    @SerializedName("last_name")
    @Expose
    var lastName: String,

    @SerializedName("avatar")
    @Expose
    var avatar: PropertyListingAgentAvatarResponse,
)