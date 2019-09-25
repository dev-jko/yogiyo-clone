package com.nadarm.yogiyo.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.nadarm.yogiyo.ui.adapter.BaseListAdapter
import com.nadarm.yogiyo.ui.model.BaseItem


@BindingAdapter("bindImage")
fun bindImage(view: ImageView, srcId: Int) {
    Glide.with(view.context).load(srcId).into(view)
}

@BindingAdapter("bindImage")
fun bindImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context).load(imageUrl).into(view)
}

@BindingAdapter("bindAdapter", "bindList", requireAll = true)
fun bindList(
    view: RecyclerView,
    adapter: BaseListAdapter,
    newList: List<BaseItem>?
) {
    if (view.adapter != adapter) {
        view.adapter = adapter
        adapter.recyclerView = view
    }
    adapter.submitList(newList)
}

@BindingAdapter("bindSnapHelper")
fun bindSnapHelper(view: RecyclerView, snapHelper: SnapHelper?) {
    snapHelper?.attachToRecyclerView(view)
}