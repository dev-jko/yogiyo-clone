package com.nadarm.yogiyo.data.model

data class GetAdsResponse(
    val status: String,
    val ads: List<Ad>
)

data class Ad(
    val id: Long,
    val type: Int,
    val imageUrl: String,
    val pageUrl: String,
    val startDate: String,
    val endDate: String
)
