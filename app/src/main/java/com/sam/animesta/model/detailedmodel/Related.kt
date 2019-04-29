package com.sam.animesta.model.detailedmodel

import com.google.gson.annotations.SerializedName

data class Related(
    @SerializedName("Adaptation")
    val adaptation: List<Adaptation?>?,
    @SerializedName("Alternative version")
    val alternativeVersion: List<AlternativeVersion?>?,
    @SerializedName("Side story")
    val sideStory: List<SideStory?>?
)