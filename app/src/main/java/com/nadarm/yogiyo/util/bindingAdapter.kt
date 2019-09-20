package com.nadarm.yogiyo.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.nadarm.yogiyo.ui.adapter.ViewHolder
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
fun submitList(
    view: View,
    adapter: ListAdapter<BaseItem.SingleItem, ViewHolder>,
    newList: List<BaseItem.SingleItem>
) {
    adapter.submitList(newList)
}

@BindingAdapter("bindSnapHelper")
fun bindSnapHelper(view: RecyclerView, snapHelper: SnapHelper?) {
    snapHelper?.attachToRecyclerView(view)
}