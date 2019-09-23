package com.nadarm.yogiyo.data.cache

import com.nadarm.yogiyo.data.repository.AdDataSource
import com.nadarm.yogiyo.ui.model.Ad
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdCacheDataSource @Inject constructor() : AdDataSource.Cache {

    private val ads: MutableList<Ad> = ArrayList()

    init {
        //TODO Ad 캐시 데이터
        ads.addAll(
            listOf(
                Ad(1, 1, "https://i.imgur.com/Gg7P9AC.png", "https://google.com"),
                Ad(2, 1, "https://i.imgur.com/ArJkOL5.png", "https://google.com"),
                Ad(3, 1, "https://i.imgur.com/N37AANO.png", "https://google.com"),
                Ad(4, 1, "https://i.imgur.com/oFc0scN.png", "https://google.com")
            )
        )
    }

    override fun getAds(): List<Ad> {
        return ads
    }
}