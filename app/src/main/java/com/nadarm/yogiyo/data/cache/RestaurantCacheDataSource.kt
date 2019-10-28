package com.nadarm.yogiyo.data.cache

import com.nadarm.yogiyo.data.repository.RestaurantDataSource
import com.nadarm.yogiyo.ui.model.*
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantCacheDataSource @Inject constructor() : RestaurantDataSource.Cache {

    private val restaurants: MutableList<Restaurant> = ArrayList()
    private val plusRestaurants: MutableList<PlusRestaurant> = ArrayList()
    private val restaurantDetail: RestaurantDetail = RestaurantDetail(
        Restaurant(
            1,
            "카페마마스 잠실점",
            listOf("야식", "프렌차이즈", "한식"),
            "https://i.imgur.com/dlFdn4F.png",
            "역삼동",
            127.029799209808,
            37.4970170754811,
            "11:00 - 01:00",
            60,
            "구이삼겹 1인, 구이삼겹 2인",
            2000,
            12000,
            "creditcard::online",
            true
        ),
        11,
        listOf(
            LabeledDishes(
                "인기 메뉴", listOf(
                    Dish(1, "국물 떡볶이", 1, "인기 메뉴", "사이즈 선택", 4500),
                    Dish(2, "마라탕", 1, "인기 메뉴", "사이즈 선택", 4500)
                )
            ),
            LabeledDishes(
                "식사 메뉴", listOf(
                    Dish(1, "국물 떡볶이", 1, "식사 메뉴", "사이즈 선택", 4500),
                    Dish(2, "마라탕", 1, "식사 메뉴", "사이즈 선택", 4500)
                )
            ),
            LabeledDishes(
                "사이드 메뉴", listOf(
                    Dish(1, "국물 떡볶이", 1, "사이드 메뉴", "사이즈 선택", 4500),
                    Dish(2, "마라탕", 1, "사이드 메뉴", "사이즈 선택", 4500)
                )
            )
        )
    )

    init {
        //TODO restaurant 캐시 데이터
        val items = listOf(
            Restaurant(
                1,
                "카페마마스 잠실점",
                listOf("야식", "프렌차이즈", "한식"),
                "https://i.imgur.com/dlFdn4F.png",
                "역삼동",
                127.029799209808,
                37.4970170754811,
                "11:00 - 01:00",
                60,
                "구이삼겹 1인, 구이삼겹 2인",
                2000,
                12000,
                "creditcard::online",
                true
            ),
            Restaurant(
                2,
                "카페마마스 잠실점",
                listOf("야식", "프렌차이즈", "한식"),
                "https://i.imgur.com/dlFdn4F.png",
                "역삼동",
                127.029799209808,
                37.4970170754811,
                "11:00 - 01:00",
                60,
                "구이삼겹 1인, 구이삼겹 2인",
                2000,
                12000,
                "creditcard::online",
                true
            ), Restaurant(
                3,
                "카페마마스 잠실점",
                listOf("야식", "프렌차이즈", "한식"),
                "https://i.imgur.com/dlFdn4F.png",
                "역삼동",
                127.029799209808,
                37.4970170754811,
                "11:00 - 01:00",
                60,
                "구이삼겹 1인, 구이삼겹 2인",
                2000,
                12000,
                "creditcard::online",
                true
            ),
            Restaurant(
                1,
                "카페마마스 잠실점",
                listOf("야식", "프렌차이즈", "한식"),
                "https://i.imgur.com/dlFdn4F.png",
                "역삼동",
                127.029799209808,
                37.4970170754811,
                "11:00 - 01:00",
                60,
                "구이삼겹 1인, 구이삼겹 2인",
                2000,
                12000,
                "creditcard::online",
                false
            ),
            Restaurant(
                2,
                "카페마마스 잠실점",
                listOf("야식", "프렌차이즈", "한식"),
                "https://i.imgur.com/dlFdn4F.png",
                "역삼동",
                127.029799209808,
                37.4970170754811,
                "11:00 - 01:00",
                60,
                "구이삼겹 1인, 구이삼겹 2인",
                2000,
                12000,
                "creditcard::online",
                false
            ), Restaurant(
                3,
                "카페마마스 잠실점",
                listOf("야식", "프렌차이즈", "한식"),
                "https://i.imgur.com/dlFdn4F.png",
                "역삼동",
                127.029799209808,
                37.4970170754811,
                "11:00 - 01:00",
                60,
                "구이삼겹 1인, 구이삼겹 2인",
                2000,
                12000,
                "creditcard::online",
                false
            )
        )

        val items2 = listOf(
            PlusRestaurant(
                1,
                "카페마마스 잠실점",
                listOf("야식", "프렌차이즈", "한식"),
                "https://i.imgur.com/dlFdn4F.png",
                "역삼동",
                127.029799209808,
                37.4970170754811,
                "11:00 - 01:00",
                60,
                "구이삼겹 1인, 구이삼겹 2인",
                2000,
                12000,
                "creditcard::online",
                true
            ),
            PlusRestaurant(
                2,
                "카페마마스 잠실점",
                listOf("야식", "프렌차이즈", "한식"),
                "https://i.imgur.com/dlFdn4F.png",
                "역삼동",
                127.029799209808,
                37.4970170754811,
                "11:00 - 01:00",
                60,
                "구이삼겹 1인, 구이삼겹 2인",
                2000,
                12000,
                "creditcard::online",
                true
            ), PlusRestaurant(
                3,
                "카페마마스 잠실점",
                listOf("야식", "프렌차이즈", "한식"),
                "https://i.imgur.com/dlFdn4F.png",
                "역삼동",
                127.029799209808,
                37.4970170754811,
                "11:00 - 01:00",
                60,
                "구이삼겹 1인, 구이삼겹 2인",
                2000,
                12000,
                "creditcard::online",
                true
            ),
            PlusRestaurant(
                1,
                "카페마마스 잠실점",
                listOf("야식", "프렌차이즈", "한식"),
                "https://i.imgur.com/dlFdn4F.png",
                "역삼동",
                127.029799209808,
                37.4970170754811,
                "11:00 - 01:00",
                60,
                "구이삼겹 1인, 구이삼겹 2인",
                2000,
                12000,
                "creditcard::online",
                false
            ),
            PlusRestaurant(
                2,
                "카페마마스 잠실점",
                listOf("야식", "프렌차이즈", "한식"),
                "https://i.imgur.com/dlFdn4F.png",
                "역삼동",
                127.029799209808,
                37.4970170754811,
                "11:00 - 01:00",
                60,
                "구이삼겹 1인, 구이삼겹 2인",
                2000,
                12000,
                "creditcard::online",
                false
            ), PlusRestaurant(
                3,
                "카페마마스 잠실점",
                listOf("야식", "프렌차이즈", "한식"),
                "https://i.imgur.com/dlFdn4F.png",
                "역삼동",
                127.029799209808,
                37.4970170754811,
                "11:00 - 01:00",
                60,
                "구이삼겹 1인, 구이삼겹 2인",
                2000,
                12000,
                "creditcard::online",
                false
            )
        )

        restaurants.addAll(items + items + items)
        plusRestaurants.addAll(items2 + items2 + items2)
    }

    override fun getRestaurants(isPlus: Boolean, category: Long): Single<List<Restaurant>> {
//        val restaurants = this.restaurants
//            .filter { it.isPlus == isPlus }
//            .filter { it.categories.contains(category) }
//        return Single.just(restaurants)
        val result = if (isPlus) {
            plusRestaurants
        } else {
            restaurants
        }
        return Single.just(result)
    }

    override fun getRestaurantDetail(restaurantId: Long): Single<RestaurantDetail> {
        return Single.just(restaurantDetail)
    }
}