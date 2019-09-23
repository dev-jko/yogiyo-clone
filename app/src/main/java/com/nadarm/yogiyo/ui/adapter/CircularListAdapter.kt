package com.nadarm.yogiyo.ui.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nadarm.yogiyo.ui.model.BaseItem

open class CircularListAdapter(
    delegate: BaseItem.Delegate? = null
) : SingleItemListAdapter(delegate) {

    protected var recyclerView: RecyclerView? = null
    private var scrollListener: RecyclerView.OnScrollListener? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    private fun addScrollListener() {
        scrollListener?.let {
            recyclerView?.removeOnScrollListener(it)
        }
        scrollListener = createScrollListener().also {
            recyclerView?.addOnScrollListener(it)
        }
    }

    open fun createScrollListener() =
        CircularScrollListener(itemCount, recyclerView?.layoutManager as LinearLayoutManager)

    override fun submitList(list: MutableList<BaseItem.SingleItem>?) {
        if (list?.size!! > 1) {
            list.add(0, list.last())
            list.add(list[1])
        }
        super.submitList(list)
        addScrollListener()
        recyclerView?.scrollToPosition(1)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = position % itemCount
        super.onBindViewHolder(holder, pos)
    }


    open class CircularScrollListener(
        private val itemCount: Int,
        private val layoutManager: LinearLayoutManager
    ) : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
            if (firstVisibleItem == itemCount - 1) {
                recyclerView.scrollToPosition(1)
            } else if (firstVisibleItem == 0) {
                recyclerView.scrollToPosition(itemCount - 1)
            }
        }
    }
}