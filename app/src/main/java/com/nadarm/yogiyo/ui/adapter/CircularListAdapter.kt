package com.nadarm.yogiyo.ui.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nadarm.yogiyo.ui.model.BaseItem

class CircularListAdapter : SingleItemListAdapter() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                if (firstVisibleItem == itemCount - 1) {
                    recyclerView.scrollToPosition(1)
                } else if (firstVisibleItem == 0) {
                    recyclerView.scrollToPosition(itemCount - 1)
                }
            }
        })
    }

    override fun submitList(list: MutableList<BaseItem.SingleItem>?) {
        if (list?.size!! > 1) {
            list.add(0, list.last())
            list.add(list[1])
        }
        super.submitList(list)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = position % itemCount
        super.onBindViewHolder(holder, pos)
    }

}