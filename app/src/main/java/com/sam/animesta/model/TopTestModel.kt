package com.sam.animesta.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "TestTable")
data class TopTestModel(
    @SerializedName("end_date")
    val endDate: String?,
    @SerializedName("episodes")
    val episodes: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("mal_id")
    val malId: Int?,
    @SerializedName("members")
    val members: Int?,
    @SerializedName("rank")
    val rank: Int?,
    @SerializedName("score")
    val score: Double?,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?,
    @PrimaryKey(autoGenerate = true)
    var id: Int
)