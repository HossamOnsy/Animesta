package com.sam.animesta.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull


@Entity(tableName = "TopModelData", primaryKeys = arrayOf("rank", "mal_id", "type"))
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
    val score: Double,
    @SerializedName("start_date")
    var startDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String?

) : Parcelable {
    fun convertScore() : Int{
        return (score/2).toInt()
    }
    constructor(source: Parcel) : this(
        source.readString(),
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readString(),
        source.readInt(),
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readInt(),
        source.readValue(Double::class.java.classLoader) as Double,
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(end_date)
        writeValue(episodes)
        writeValue(volumes)
        writeString(image_url)
        writeInt(mal_id)
        writeValue(members)
        writeInt(rank)
        writeValue(score)
        writeString(startDate)
        writeString(title)
        writeString(type)
        writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TopModel> = object : Parcelable.Creator<TopModel> {
            override fun createFromParcel(source: Parcel): TopModel = TopModel(source)
            override fun newArray(size: Int): Array<TopModel?> = arrayOfNulls(size)
        }
    }
}