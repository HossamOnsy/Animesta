package com.sam.animesta.model.detailedmodel

import com.google.gson.annotations.SerializedName

data class SideStory(
    @SerializedName("mal_id")
    val malId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)