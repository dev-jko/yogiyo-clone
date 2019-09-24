package com.nadarm.yogiyo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nadarm.yogiyo.ui.model.BaseItem

open class BaseListAdapter(
    private val delegate: BaseItem.Delegate? = null
) : ListAdapter<BaseItem, ViewHolder>(
    object : DiffUtil.ItemCallback<BaseItem>() {
        override fun areItemsTheSame(
            oldItem: BaseItem,
            newItem: BaseItem
        ): Boolean {
            return oldItem.getType() == newItem.getType()
        }

        override fun areContentsTheSame(
            oldItem: BaseItem,
            newItem: BaseItem
        ): Boolean {
            return oldItem.isSame(newItem)
        }
    }
) {

    var recyclerView: RecyclerView? = null

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return ViewHolder(binding, delegate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}