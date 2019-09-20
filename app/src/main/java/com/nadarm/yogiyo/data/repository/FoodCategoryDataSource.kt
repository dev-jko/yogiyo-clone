package com.nadarm.yogiyo.data.repository

interface FoodCategoryDataSource : FoodCategoryRepository {
    interface Cache : FoodCategoryDataSource
}