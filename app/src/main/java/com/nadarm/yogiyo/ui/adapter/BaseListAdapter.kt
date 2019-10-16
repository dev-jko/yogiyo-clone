package com.nadarm.yogiyo.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nadarm.yogiyo.ui.model.BaseItem
import com.nadarm.yogiyo.ui.viewHolder.ItemViewHolder
import com.nadarm.yogiyo.ui.viewHolder.ViewHolderFactory

open class BaseListAdapter(
    private val delegate: Delegate
) : ListAdapter<BaseItem, ItemViewHolder>(

    object : DiffUtil.ItemCallback<BaseItem>() {
        override fun areItemsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean {
            return ViewHolderFactory.getItemViewType(oldItem) ==
                    ViewHolderFactory.getItemViewType(newItem)
        }

        override fun areContentsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean {
            return ViewHolderFactory.areSame(oldItem, newItem)
        }
    }
) {
    private var recyclerView: RecyclerView? = null

    fun getRecyclerView(): RecyclerView? = recyclerView

    fun setRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        addScrollListener()
    }

    private fun addScrollListener() {
        val listener: RecyclerView.OnScrollListener = createScrollListener()
        getRecyclerView()?.addOnScrollListener(listener)
    }

    protected open fun createScrollListener(): RecyclerView.OnScrollListener {
        return BaseScrollListener(delegate)
    }

    override fun getItemViewType(position: Int): Int {
        return ViewHolderFactory.getItemViewType(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ViewHolderFactory.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, delegate)
    }

    interface Delegate {
        fun itemClicked(item: BaseItem)
        fun scrollChanged(delta: Pair<Int, Int>)
    }

    open class BaseScrollListener(
        private val delegate: Delegate
    ) : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            delegate.scrollChanged(dx to dy)
        }


    }

}