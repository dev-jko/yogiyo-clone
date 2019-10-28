package com.nadarm.yogiyo.data.repository

import com.nadarm.yogiyo.ui.model.Restaurant
import com.nadarm.yogiyo.ui.model.RestaurantDetail
import io.reactivex.Single

interface RestaurantRepository {

    fun getRestaurants(isPlus: Boolean, category: Long): Single<List<Restaurant>>

    fun getRestaurantDetail(restaurantId:Long):Single<RestaurantDetail>


}

interface RestaurantDataSource : RestaurantRepository {
    interface Cache : RestaurantDataSource

}