package com.nadarm.yogiyo.data

import com.nadarm.yogiyo.data.cache.AdCacheDataSource
import com.nadarm.yogiyo.data.cache.FoodCategoryCacheDataSource
import com.nadarm.yogiyo.data.cache.RestaurantCacheDataSource
import com.nadarm.yogiyo.data.remote.FoodCategoryRemoteDataSource
import com.nadarm.yogiyo.data.remote.api.FoodCategoryService
import com.nadarm.yogiyo.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [DataProviderModule::class])
interface DataBindingModule {

    @Singleton
    @Binds
    fun bindFoodCategoryRepository(repository: FoodCategoryDataRepository): FoodCategoryRepository

    @Singleton
    @Binds
    fun bindFoodCategoryCacheDataSource(dataSource: FoodCategoryCacheDataSource): FoodCategoryDataSource.Cache

    @Singleton
    @Binds
    fun bindFoodCategoryRemoteDataSource(dataSource: FoodCategoryRemoteDataSource): FoodCategoryDataSource.Remote

    @Singleton
    @Binds
    fun bindAdRepository(repository: AdDataRepository): AdRepository

    @Singleton
    @Binds
    fun bindAdCacheDataSource(dataSource: AdCacheDataSource): AdDataSource.Cache

    @Singleton
    @Binds
    fun bindRestaurantRepository(repository: RestaurantDataRepository): RestaurantRepository

    @Singleton
    @Binds
    fun bindRestaurantCacheDataSource(dataSource: RestaurantCacheDataSource): RestaurantDataSource.Cache
}

@Module
object DataProviderModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideFoodCateogryService(url: String): FoodCategoryService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(FoodCategoryService::class.java)
    }


}