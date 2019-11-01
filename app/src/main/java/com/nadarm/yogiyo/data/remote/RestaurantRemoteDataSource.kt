package com.nadarm.yogiyo.data.remote

import com.nadarm.yogiyo.data.remote.api.RestaurantRetrofit
import com.nadarm.yogiyo.data.repository.RestaurantDataSource
import com.nadarm.yogiyo.ui.model.Restaurant
import com.nadarm.yogiyo.ui.model.RestaurantDetail
import com.nadarm.yogiyo.util.mapRestaurantsFromData
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRemoteDataSource @Inject constructor(
    private val retrofit: RestaurantRetrofit
) : RestaurantDataSource.Remote {

    override fun getRestaurants(
        isPlus: Boolean,
        categoryId: Long,
        token: String
    ): Single<List<Restaurant>> {
        return if (isPlus) {
            retrofit.getRestaurants(categoryId, token)
        } else {
            retrofit.getPlusRestaurants(categoryId, token)
        }.map { it.mapRestaurantsFromData() }
    }

    override fun getRestaurantDetail(
        restaurantId: Long,
        baseUrl: String,
        token: String
    ): Single<RestaurantDetail> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}