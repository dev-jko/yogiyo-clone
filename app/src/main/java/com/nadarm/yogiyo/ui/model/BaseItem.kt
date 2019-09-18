package com.nadarm.yogiyo.ui.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView

sealed class BaseItem() {
    private val type = -1
    fun getType(): Int = type
    fun getItem():Any =

    class ListItem(private val item: List<Any>) : BaseItem() {
        private val type = 0
    }

}

