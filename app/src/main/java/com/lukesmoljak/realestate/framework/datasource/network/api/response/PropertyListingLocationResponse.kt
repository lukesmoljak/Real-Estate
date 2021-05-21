package com.lukesmoljak.realestate.framework.datasource.network.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PropertyListingLocationResponse(

    @SerializedName("address")
    @Expose
    var address: String,

    @SerializedName("suburb")
    @Expose
    var suburb: String,
)