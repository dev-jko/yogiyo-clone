package com.nadarm.yogiyo.data.repository

import com.nadarm.yogiyo.ui.model.Ad
import io.reactivex.Single

interface AdRepository {

    fun getAds(type: Int): Single<List<Ad>>
}

interface AdDataSource : AdRepository {
    interface Cache : AdDataSource
}