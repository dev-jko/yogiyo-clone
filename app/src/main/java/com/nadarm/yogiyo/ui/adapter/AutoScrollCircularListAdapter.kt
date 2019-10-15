package com.nadarm.yogiyo.ui.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AutoScrollCircularListAdapter(
    private val delegate: Delegate
) : BaseListAdapter(delegate) {

    override fun setRecyclerView(recyclerView: RecyclerView) {
        super.setRecyclerView(recyclerView)
        addScrollListener()
    }

    private fun addScrollListener() {
        val listener = AutoScrollListener(
            getRecyclerView()?.layoutManager as LinearLayoutManager,
            delegate
        )
        getRecyclerView()?.addOnScrollListener(listener)
    }

    interface Delegate : BaseListAdapter.Delegate {
        fun scrollPositionChanged(position: Int)
        fun scrollStateChanged(state: Int)
    }

    class AutoScrollListener(
        private val layoutManager: LinearLayoutManager,
        private val delegate: Delegate
    ) : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            delegate.scrollPositionChanged(layoutManager.findFirstCompletelyVisibleItemPosition())
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            delegate.scrollStateChanged(newState)
        }
    }
}