package com.nadarm.yogiyo.ui.model

import androidx.recyclerview.widget.SnapHelper
import com.nadarm.yogiyo.R
import com.nadarm.yogiyo.ui.adapter.BaseListAdapter

data class HorizontalList(
    adapter: BaseListAdapter,
    override val snapHelper: SnapHelper?
) : BaseItem.ListItem(adapter)