package com.nadarm.yogiyo.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.nadarm.yogiyo.ui.adapter.BaseListAdapter
import com.nadarm.yogiyo.ui.listener.BaseScrollListener


@BindingAdapter("bindImage")
fun bindImage(view: ImageView, srcId: Int) {
    Glide.with(view.context).load(srcId).into(view)
}

@BindingAdapter("bindImage")
fun bindImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context).load(imageUrl).into(view)
}

@BindingAdapter("bindAdapter")
fun bindAdapter(
    view: RecyclerView,
    adapter: BaseListAdapter
) {
    adapter.setRecyclerView(view)
    view.adapter = adapter
}

@BindingAdapter("bindScrollListener")
fun bindScrollListener(view: RecyclerView, scrollListener: BaseScrollListener?) {
    if (scrollListener != null) {
        view.addOnScrollListener(scrollListener)
        scrollListener.layoutManager = view.layoutManager
        scrollListener.init()
    }
}

@BindingAdapter("bindSnapHelper")
fun bindSnapHelper(view: RecyclerView, snapHelper: SnapHelper?) {
    snapHelper?.attachToRecyclerView(view)
}