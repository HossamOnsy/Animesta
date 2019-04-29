package com.sam.animesta.model.detailedmodel

import com.google.gson.annotations.SerializedName

data class AnimeDetailsModel(
    @SerializedName("aired")
    val aired: Aired?,
    @SerializedName("airing")
    val airing: Boolean?,
    @SerializedName("background")
    val background: String?,
    @SerializedName("broadcast")
    val broadcast: String?,
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("ending_themes")
    val endingThemes: List<String?>?,
    @SerializedName("episodes")
    val episodes: Int?,
    @SerializedName("favorites")
    val favorites: Int?,
    @SerializedName("genres")
    val genres: List<Genre?>?,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("licensors")
    val licensors: List<Licensor?>?,
    @SerializedName("mal_id")
    val malId: Int?,
    @SerializedName("members")
    val members: Int?,
    @SerializedName("opening_themes")
    val openingThemes: List<String?>?,
    @SerializedName("popularity")
    val popularity: Int?,
    @SerializedName("premiered")
    val premiered: String?,
    @SerializedName("producers")
    val producers: List<Producer?>?,
    @SerializedName("rank")
    val rank: Int?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("related")
    val related: Related?,
    @SerializedName("request_cache_expiry")
    val requestCacheExpiry: Int?,
    @SerializedName("request_cached")
    val requestCached: Boolean?,
    @SerializedName("request_hash")
    val requestHash: String?,
    @SerializedName("score")
    val score: Double?,
    @SerializedName("scored_by")
    val scoredBy: Int?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("studios")
    val studios: List<Studio?>?,
    @SerializedName("synopsis")
    val synopsis: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("title_english")
    val titleEnglish: String?,
    @SerializedName("title_japanese")
    val titleJapanese: String?,
    @SerializedName("title_synonyms")
    val titleSynonyms: List<String?>?,
    @SerializedName("trailer_url")
    val trailerUrl: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
) {
    fun convertScore(): Int {
        if (score != null)
            return (score / 2).toInt()
        else
            return 0
    }


}