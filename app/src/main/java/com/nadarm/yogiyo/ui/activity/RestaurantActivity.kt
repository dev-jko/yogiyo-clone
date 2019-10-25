package com.nadarm.yogiyo.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.core.view.marginStart
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.databinding.DataBindingUtil
import com.google.android.material.appbar.AppBarLayout
import com.nadarm.yogiyo.R
import com.nadarm.yogiyo.databinding.ActivityRestaurantBinding
import com.nadarm.yogiyo.ui.model.Restaurant
import kotlinx.android.synthetic.main.activity_restaurant.*
import kotlin.math.max


class RestaurantActivity : BaseActivity() {

    private lateinit var binding: ActivityRestaurantBinding
    private val density by lazy { application.resources.displayMetrics.density }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant)
        binding.lifecycleOwner = this

        binding.restaurant = Restaurant(
            1,
            "카페마마스 잠실점",
            arrayOf("야식", "프렌차이즈", "한식"),
            "https://i.imgur.com/dlFdn4F.png",
            "역삼동",
            127.029799209808,
            37.4970170754811,
            "11:00 - 01:00",
            60,
            "구이삼겹 1인, 구이삼겹 2인",
            2000,
            12000,
            "creditcard::online",
            "2019-10-15T13:48:47.000Z",
            "2019-10-15T13:48:47.000Z",
            true
        )

        setSupportActionBar(toolbar)
        app_bar.addOnOffsetChangedListener(
            object : AppBarLayout.OnOffsetChangedListener {
                private var before = -1
                override fun onOffsetChanged(appBar: AppBarLayout?, offset: Int) {
                    if (before == offset || (offset < -100 && restaurant_constraint_layout.marginStart == 0)) {
                        return
                    }
                    before = offset
                    val margin = (max(0, 100 + offset) / 5f * density).toInt()
                    val padding = (20 * density - margin).toInt()
                    restaurant_constraint_layout.updatePadding(
                        padding,
                        restaurant_constraint_layout.paddingTop,
                        padding,
                        restaurant_constraint_layout.paddingBottom
                    )
                    restaurant_tab_constraint_layout.updatePadding(
                        padding,
                        restaurant_tab_layout.paddingTop,
                        padding,
                        restaurant_tab_layout.paddingBottom
                    )
                    restaurant_tab_constraint_layout.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        marginStart = margin
                        marginEnd = margin
                    }
                    restaurant_constraint_layout.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        marginStart = margin
                        marginEnd = margin
                    }
                }
            }
        )

        val restaurantId = intent.getLongExtra("restaurantId", -1)
        if (restaurantId == -1L) {
            finish()
        }


    }
}
