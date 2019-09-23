package com.nadarm.yogiyo.data.repository

import com.nadarm.yogiyo.ui.model.Ad
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdDataRepository @Inject constructor(
    private val cache: AdDataSource.Cache
) : AdRepository {


    override fun getAds(): List<Ad> {
        return cache.getAds()
    }
}