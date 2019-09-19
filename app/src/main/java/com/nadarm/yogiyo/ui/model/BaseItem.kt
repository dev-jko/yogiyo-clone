package com.nadarm.yogiyo.ui.model

import androidx.recyclerview.widget.ListAdapter
import com.nadarm.yogiyo.R
import com.nadarm.yogiyo.ui.adapter.ViewHolder

sealed class BaseItem {

    fun isSame(other: BaseItem): Boolean {
        return when (this) {
            is ListItem -> this.adapter == (other as ListItem).adapter
            is AdItem -> this.item.id == (other as AdItem).item.id
            else -> false
        }
    }

    fun getType(): Int {
        return when (this) {
            is HorizontalListItem -> R.layout.item_horizontal_list
            is AdItem -> R.layout.item_top_ad
            else -> -1
        }
    }

    abstract class ListItem(
        open val item: List<BaseItem>,
        open val adapter: ListAdapter<BaseItem, ViewHolder>
    ) : BaseItem()

    abstract class SingleItem : BaseItem()

}

data class HorizontalListItem(
    override val item: List<BaseItem>,
    override val adapter: ListAdapter<BaseItem, ViewHolder>
) : BaseItem.ListItem(item, adapter)

data class AdItem(val item: Ad) : BaseItem.SingleItem()

