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
            is BlankItem -> true
            else -> false
        }
    }

    fun getType(): Int {
        return when (this) {
            is HorizontalListItem -> R.layout.item_horizontal_list
            is GridListItem -> R.layout.item_grid_list
            is FoodCategoryItem -> R.layout.item_food_category
            is BlankItem -> R.layout.item_blank
            is AdItem -> when (item.type) {
                Ad.top -> R.layout.item_top_ad
                Ad.bottom -> R.layout.item_bottom_ad
                else -> -1
            }
            else -> -1
        }
    }

    interface Delegate {
        fun itemClicked(item: BaseItem)
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

data class GridListItem(
    override val adapter: BaseListAdapter,
    override val snapHelper: SnapHelper? = null
) : BaseItem.ListItem(adapter, snapHelper)

data class AdItem(override val item: Ad) : BaseItem.SingleItem(item)
data class FoodCategoryItem(override val item: FoodCategory) : BaseItem.SingleItem(item)