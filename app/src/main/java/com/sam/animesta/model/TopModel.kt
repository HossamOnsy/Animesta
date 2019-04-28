package com.sam.animesta.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull


@Entity(tableName = "TopModelData",primaryKeys = arrayOf("rank","mal_id","type"))
data class TopModel(
    @SerializedName("end_date")
    var end_date: String?,
    @SerializedName("episodes")
    var episodes: Int?,
    @SerializedName("volumes")
    var volumes: Int?,
    @SerializedName("image_url")
    var image_url: String?,
    @SerializedName("mal_id")
    val mal_id: Int,
    @SerializedName("members")
    val members: Int?,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("score")
    val score: Double?,
    @SerializedName("start_date")
    var startDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String?

)