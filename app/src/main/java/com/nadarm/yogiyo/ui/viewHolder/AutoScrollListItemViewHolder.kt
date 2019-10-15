package com.nadarm.yogiyo.ui.viewHolder

import androidx.databinding.ViewDataBinding
import com.nadarm.yogiyo.ui.adapter.AutoScrollCircularListAdapter
import com.nadarm.yogiyo.ui.model.BaseItem

class AutoScrollListItemViewHolder(
    binding: ViewDataBinding
) : ListItemViewHolder(binding) {

    fun bind(item: BaseItem, delegate: AutoScrollCircularListAdapter.Delegate) {
        super.bind(item, delegate)

    }
}