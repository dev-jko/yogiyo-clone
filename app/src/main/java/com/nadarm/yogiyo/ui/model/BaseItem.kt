package com.nadarm.yogiyo.ui.model

import androidx.recyclerview.widget.SnapHelper
import com.nadarm.yogiyo.R
import com.nadarm.yogiyo.ui.adapter.BaseListAdapter

sealed class BaseItem {

    fun isSame(other: BaseItem): Boolean {
        return when (this) {
            is ListItem -> this.adapter == (other as ListItem).adapter
            is AdItem -> this.item.id == (other as AdItem).item.id
            is FoodCategoryItem -> this.item.category == (other as FoodCategoryItem).item.category
            is RestaurantItem -> this.item.id == (other as RestaurantItem).item.id
            is BlankItem -> true
            else -> false
        }
    }

    fun getType(): Int {
        return when (this) {
            is HorizontalListItem -> R.layout.item_horizontal_list
            is GridListItem -> R.layout.item_grid_list
            is PlusPopularRestaurantListItem -> R.layout.item_plus_popular_restaurant_list
            is PlusNewRestaurantListItem -> R.layout.item_plus_new_restaurant_list
            is FoodCategoryItem -> R.layout.item_food_category
            is AdItem -> when (item.type) {
                Ad.top -> R.layout.item_top_ad
                Ad.bottom -> R.layout.item_bottom_ad
                else -> R.layout.item_blank
            }
            is RestaurantItem -> if (item.isPlus) {
                R.layout.item_plus_restaurant_thumbnail
            } else {
                R.layout.item_blank
            }
            else -> R.layout.item_blank
        }
    }

    abstract class ListItem(
        open val adapter: BaseListAdapter,
        open val snapHelper: SnapHelper? = null
    ) : BaseItem()

    abstract class SingleItem(
        open val item: Any
    ) : BaseItem()

    object BlankItem : BaseItem()

}

data class HorizontalListItem(
    override val adapter: BaseListAdapter,
    override val snapHelper: SnapHelper? = null
) : BaseItem.ListItem(adapter, snapHelper)

data class PlusPopularRestaurantListItem(
    override val adapter: BaseListAdapter,
    override val snapHelper: SnapHelper? = null
) : BaseItem.ListItem(adapter, snapHelper)

data class PlusNewRestaurantListItem(
    override val adapter: BaseListAdapter,
    override val snapHelper: SnapHelper? = null
) : BaseItem.ListItem(adapter, snapHelper)

data class GridListItem(
    override val adapter: BaseListAdapter,
    override val snapHelper: SnapHelper? = null
) : BaseItem.ListItem(adapter, snapHelper)

data class AdItem(override val item: Ad) : BaseItem.SingleItem(item)
data class FoodCategoryItem(override val item: FoodCategory) : BaseItem.SingleItem(item)
data class RestaurantItem(override val item: Restaurant) : BaseItem.SingleItem(item)