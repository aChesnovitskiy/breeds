package com.achesnovitskiy.breeds.data.api

import com.google.gson.annotations.SerializedName

data class BreedsResponse(
    @SerializedName("message") val message: Message,
    @SerializedName("status") val status: String
)