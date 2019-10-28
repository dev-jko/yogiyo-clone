package com.nadarm.yogiyo.data.repository

import com.nadarm.yogiyo.ui.model.Restaurant
import com.nadarm.yogiyo.ui.model.RestaurantDetail
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RestaurantDataRepository @Inject constructor(
    private val cache: RestaurantDataSource.Cache
) : RestaurantRepository {

    override fun getRestaurants(isPlus: Boolean, category: Long): Single<List<Restaurant>> {
        return cache.getRestaurants(isPlus, category)
    }

    override fun getRestaurantDetail(restaurantId: Long): Single<RestaurantDetail> {
        return cache.getRestaurantDetail(restaurantId)
    }
}