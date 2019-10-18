package com.nadarm.yogiyo.ui.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

class BaseRecyclerView(context: Context) : RecyclerView(context) {


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}