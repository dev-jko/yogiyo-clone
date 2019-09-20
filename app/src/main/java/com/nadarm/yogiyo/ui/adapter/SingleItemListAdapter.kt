package com.nadarm.yogiyo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nadarm.yogiyo.ui.model.BaseItem

open class SingleItemListAdapter : ListAdapter<BaseItem.SingleItem, ViewHolder>(
    object : DiffUtil.ItemCallback<BaseItem.SingleItem>() {
        override fun areItemsTheSame(
            oldItem: BaseItem.SingleItem,
            newItem: BaseItem.SingleItem
        ): Boolean {
            return oldItem.getType() == newItem.getType()
        }

        override fun areContentsTheSame(
            oldItem: BaseItem.SingleItem,
            newItem: BaseItem.SingleItem
        ): Boolean {
            return oldItem.isSame(newItem)
        }
    }
) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}