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
    private val delegate: Delegate? = null
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

    private var recyclerView: RecyclerView? = null

    protected fun getRecyclerView(): RecyclerView? = recyclerView

    open fun setRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

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
        if (item is BaseItem.ListItem) {
            holder.bind()
        }
        holder.bind(item)
    }

    interface Delegate {
        fun itemClicked(item: BaseItem)
    }
}