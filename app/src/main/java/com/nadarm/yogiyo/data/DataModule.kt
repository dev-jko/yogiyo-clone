package com.nadarm.yogiyo.data

import com.nadarm.yogiyo.data.cache.FoodCategoryCacheDataSource
import com.nadarm.yogiyo.data.repository.FoodCategoryDataRepository
import com.nadarm.yogiyo.data.repository.FoodCategoryDataSource
import com.nadarm.yogiyo.data.repository.FoodCategoryRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [DataProvidingModule::class])
interface DataBindingModule {

    @Singleton
    @Binds
    fun bindFoodCategoryRepository(repository: FoodCategoryDataRepository): FoodCategoryRepository

    @Singleton
    @Binds
    fun bindFoodCategoryCacheDataSource(dataSource: FoodCategoryCacheDataSource): FoodCategoryDataSource.Cache

}

@Module
object DataProvidingModule {


}