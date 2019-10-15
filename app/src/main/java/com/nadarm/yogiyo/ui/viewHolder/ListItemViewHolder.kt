package com.nadarm.yogiyo.ui.viewHolder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.nadarm.yogiyo.ui.adapter.BaseListAdapter
import com.nadarm.yogiyo.ui.model.BaseItem

open class ListItemViewHolder(
    binding: ViewDataBinding
) : ItemViewHolder(binding) {

    protected val recyclerView: RecyclerView =
        binding.root.findViewWithTag<RecyclerView>("recyclerView")

    override fun bind(item: BaseItem, delegate: BaseListAdapter.Delegate?) {
        if (item is BaseItem.ListItem) {
            item.adapter.setRecyclerView(recyclerView)
        }
        super.bind(item, delegate)
    }
}