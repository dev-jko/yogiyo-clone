package com.nadarm.yogiyo.ui.model

import androidx.recyclerview.widget.SnapHelper
import com.nadarm.yogiyo.ui.adapter.BaseListAdapter

data class PlusNewRestaurantList(
    override val adapter: BaseListAdapter,
    override val snapHelper: SnapHelper?
) : BaseItem.ListItem(adapter, snapHelper)