package com.nadarm.yogiyo.ui.model

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.SnapHelper
import com.nadarm.yogiyo.R
import com.nadarm.yogiyo.ui.adapter.ViewHolder

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
            is AdItem -> R.layout.item_top_ad
            is FoodCategoryItem -> R.layout.item_food_category
            is BlankItem -> R.layout.item_blank
            else -> -1
        }
    }

    abstract class ListItem(
        open val item: List<SingleItem>,
        open val adapter: ListAdapter<SingleItem, ViewHolder>,
        open val snapHelper: SnapHelper? = null
    ) : BaseItem()

    abstract class SingleItem(
        open val item: Any
    ) : BaseItem()

    object BlankItem : BaseItem()

}

data class HorizontalListItem(
    override val item: List<SingleItem>,
    override val adapter: ListAdapter<SingleItem, ViewHolder>,
    override val snapHelper: SnapHelper? = null
) : BaseItem.ListItem(item, adapter, snapHelper)

data class GridListItem(
    override val item: List<SingleItem>,
    override val adapter: ListAdapter<SingleItem, ViewHolder>,
    override val snapHelper: SnapHelper? = null
) : BaseItem.ListItem(item, adapter, snapHelper)

data class AdItem(override val item: Ad) : BaseItem.SingleItem(item)
data class FoodCategoryItem(override val item: FoodCategory) : BaseItem.SingleItem(item)