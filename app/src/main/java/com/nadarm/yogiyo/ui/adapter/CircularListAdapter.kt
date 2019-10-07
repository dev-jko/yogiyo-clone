package com.nadarm.yogiyo.ui.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nadarm.yogiyo.ui.model.BaseItem

open class CircularListAdapter(
    private val delegate: Delegate? = null
) : BaseListAdapter(delegate) {

    private var scrollListener: CircularScrollListener? = null

    override fun setRecyclerView(recyclerView: RecyclerView) {
        super.setRecyclerView(recyclerView)
        addScrollListener()
    }

    private fun addScrollListener() {
        scrollListener?.let {
            getRecyclerView()?.removeOnScrollListener(it)
        }
        scrollListener = createScrollListener().also {
            getRecyclerView()?.addOnScrollListener(it)
        }
    }

    open fun createScrollListener() =
        CircularScrollListener(
            itemCount,
            getRecyclerView()?.layoutManager as LinearLayoutManager,
            delegate
        )

    override fun submitList(list: MutableList<BaseItem>?) {
        if (list != null && list.size > 1) {
            list.add(0, list.last())
            list.add(list[1])
        }
        super.submitList(list)
        if (list != null && list.size > 1) {
            getRecyclerView()?.let {
                addScrollListener()
                it.scrollToPosition(1)
            }
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val pos = position % itemCount
        super.onBindViewHolder(holder, pos)
    }

    interface Delegate : BaseListAdapter.Delegate {
        fun scrollPositionChanged(position: Int)
    }

    open class CircularScrollListener(
        open var itemCount: Int,
        private val layoutManager: LinearLayoutManager,
        private val delegate: Delegate?
    ) : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            delegate?.scrollPositionChanged(layoutManager.findFirstVisibleItemPosition())

            val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
            if (firstVisibleItem == itemCount - 1) {
                recyclerView.scrollToPosition(1)
            } else if (firstVisibleItem == 0) {
                recyclerView.scrollToPosition(itemCount - 1)
            }
        }
    }
}