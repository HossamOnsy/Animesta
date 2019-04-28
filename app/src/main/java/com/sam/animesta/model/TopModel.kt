package com.sam.animesta.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "TopModelData")
data class TopModel(
    @SerializedName("end_date")
    var end_date: String?,
    @SerializedName("episodes")
    var episodes: Int?,
    @SerializedName("image_url")
    var image_url: String?,
    @PrimaryKey (autoGenerate = false)
    @SerializedName("mal_id")
    val malId: Int?,
    @SerializedName("members")
    val members: Int?,
    @SerializedName("rank")
    val rank: Int?,
    @SerializedName("score")
    val score: Double?,
    @SerializedName("start_date")
    var startDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?

) 