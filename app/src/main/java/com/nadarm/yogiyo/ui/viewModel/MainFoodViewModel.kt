package com.nadarm.yogiyo.ui.viewModel

import com.nadarm.yogiyo.data.repository.FoodCategoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainFoodViewModel @Inject constructor(
    private val foodCategoryRepo: FoodCategoryRepository
) : BaseViewModel() {

}
