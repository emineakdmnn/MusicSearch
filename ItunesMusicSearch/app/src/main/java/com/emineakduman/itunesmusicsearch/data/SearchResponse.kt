package com.emineakduman.itunesmusicsearch.data

data class SearchResponse(
    val resultCount: Int,
    val results: List<Track>
)