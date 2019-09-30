package com.nadarm.yogiyo.ui.model

data class Ad(
    val id: Long,
    val type: Int,
    val imageUrl: String,
    val pageUrl: String
) {
    companion object {
        const val top: Int = 1
        const val bottom: Int = 2
    }
}