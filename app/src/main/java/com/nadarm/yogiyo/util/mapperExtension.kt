package com.nadarm.yogiyo.data.model

import com.nadarm.yogiyo.ui.model.FoodCategory

object Mapper {

    fun mapToData(category: FoodCategory): Category {
        return Category(category.id, category.name, category.imgUrl)
    }

    fun mapToData(categories: List<FoodCategory>): List<Category> {
        return categories.map(this::mapToData)
    }

    fun mapFromData(category: Category, baseUrl: String): FoodCategory {
        return FoodCategory(category.id, category.name, baseUrl + category.imgUrl)
    }

    fun mapCategoriesFromData(categories: List<Category>, baseUrl: String): List<FoodCategory> {
        return categories.map { mapFromData(it, baseUrl) }
    }

    fun mapFromData(ad: Ad, baseUrl: String): com.nadarm.yogiyo.ui.model.Ad {
        val type = when (ad.type) {
            "mainAd" -> com.nadarm.yogiyo.ui.model.Ad.Type.Large
            else -> com.nadarm.yogiyo.ui.model.Ad.Type.Small
        }
        return com.nadarm.yogiyo.ui.model.Ad(
            ad.id,
            type,
            baseUrl + ad.imgUrl
        )
    }

    fun mapAdsFromData(ads: List<Ad>, baseUrl: String): List<com.nadarm.yogiyo.ui.model.Ad> {
        return ads.map { mapFromData(it, baseUrl) }
    }

}