package com.nadarm.yogiyo.ui.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nadarm.yogiyo.ui.model.BaseItem
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class AutoScrollCircularListAdapter(
    private val interval: Long = 3000,
    delegate: BaseItem.Delegate? = null
) : CircularListAdapter(delegate) {

    private var autoScrollDisposable: Disposable? = null

    override fun submitList(list: MutableList<BaseItem.SingleItem>?) {
        super.submitList(list)
        if (itemCount > 1) {
            autoScroll(1)
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        autoScrollDisposable?.dispose()
        super.onDetachedFromRecyclerView(recyclerView)
    }

    override fun createScrollListener(): CircularScrollListener {
        return AutoScrollListener(
            itemCount,
            recyclerView?.layoutManager as LinearLayoutManager
        ) { state, layoutManager ->
            when (state) {
                RecyclerView.SCROLL_STATE_DRAGGING -> autoScrollDisposable?.dispose()
                RecyclerView.SCROLL_STATE_IDLE -> autoScroll(layoutManager.findFirstVisibleItemPosition())
            }
        }
    }

    private fun autoScroll(startItem: Int) {
        autoScrollDisposable?.let {
            if (!it.isDisposed) return
        }
        autoScrollDisposable = Flowable.interval(interval, TimeUnit.MILLISECONDS)
            .map { (it.toInt() + startItem - 1) % (itemCount - 2) + 2 }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                this.recyclerView?.smoothScrollToPosition(it)
            }
        // TODO Error 처리 하기
    }

    class AutoScrollListener(
        itemCount: Int,
        private val layoutManager: LinearLayoutManager,
        private val stateChanged: (Int, LinearLayoutManager) -> Unit
    ) : CircularScrollListener(itemCount, layoutManager) {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            stateChanged(newState, layoutManager)
        }
    }
}