package com.nadarm.yogiyo.data.remote.api

import com.nadarm.yogiyo.data.model.Restaurant
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRetrofit @Inject constructor(
    private val service: RestaurantService
) {

    fun getRestaurants(categoryId: Long, token: String): Single<List<Restaurant>> {
        return service.getRestaurants(categoryId, token)
            .map { it.restaurants }
    }

    fun getPlusRestaurants(categoryId: Long, token: String): Single<List<Restaurant>> {
        return service.getPlusRestaurants(categoryId, token)
            .map { it.restaurants }
    }
}