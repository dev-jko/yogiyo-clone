package com.nadarm.yogiyo.data.repository

import com.nadarm.yogiyo.ui.model.Ad

interface AdRepository {

    fun getAds(): List<Ad>
}

interface AdDataSource : AdRepository {
    interface Cache : AdDataSource
}