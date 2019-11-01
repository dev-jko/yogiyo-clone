package com.nadarm.yogiyo.data.repository

import com.nadarm.yogiyo.ui.model.Restaurant
import com.nadarm.yogiyo.ui.model.RestaurantDetail
import io.reactivex.Single

interface RestaurantRepository {

    fun getRestaurants(
        isPlus: Boolean,
        categoryId: Long,
        token: String
    ): Single<List<Restaurant>>

    fun getRestaurantDetail(
        restaurantId: Long,
        baseUrl: String,
        token: String
    ): Single<RestaurantDetail>


}

interface RestaurantDataSource : RestaurantRepository {
    interface Cache : RestaurantDataSource
    interface Remote : RestaurantDataSource
}