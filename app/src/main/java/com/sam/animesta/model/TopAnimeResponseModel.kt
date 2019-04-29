package com.sam.animesta.model


data class TopAnimeResponseModel(
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val top: List<TopModel>,
    val results: List<TopModel>
) {

}