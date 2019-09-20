package com.nadarm.yogiyo.data.repository

import com.nadarm.yogiyo.ui.model.FoodCategory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FoodCategoryDataRepository @Inject constructor(
    private val cache: FoodCategoryDataSource.Cache
) : FoodCategoryRepository {

    override fun getCategories(): List<FoodCategory> {
        return cache.getCategories()
    }
}