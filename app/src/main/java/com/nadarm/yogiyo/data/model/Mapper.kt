package com.nadarm.yogiyo.data.model

import com.nadarm.yogiyo.ui.model.FoodCategory

object Mapper {

    fun mapToData(category: FoodCategory): Category {
        return Category(category.id, category.name, category.imgUrl)
    }

    fun mapToData(categories: List<FoodCategory>): List<Category> {
        return categories.map(this::mapToData)
    }

    fun mapFromData(category: Category): FoodCategory {
        return FoodCategory(category.id, category.name, category.imgUrl)
    }

    fun mapFromData(categories: List<Category>): List<FoodCategory> {
        return categories.map(this::mapFromData)
    }


}