package com.nadarm.yogiyo.data.repository

import com.nadarm.yogiyo.ui.model.FoodCategory

interface FoodCategoryRepository {

    fun getCategories(): List<FoodCategory>

}

interface FoodCategoryDataSource : FoodCategoryRepository {
    interface Cache : FoodCategoryDataSource
}