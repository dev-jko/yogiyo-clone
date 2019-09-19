package com.nadarm.yogiyo.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.nadarm.yogiyo.BR
import com.nadarm.yogiyo.ui.model.BaseItem


class ViewHolder(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BaseItem) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}