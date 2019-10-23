package com.nadarm.yogiyo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.nadarm.yogiyo.R
import com.nadarm.yogiyo.databinding.FragmentFoodTabBinding
import com.nadarm.yogiyo.di.ActivityScope
import com.nadarm.yogiyo.ui.adapter.FoodCategoryPagerAdapter
import com.nadarm.yogiyo.ui.model.FoodCategory
import com.nadarm.yogiyo.ui.viewModel.FoodCategoryViewModel
import com.nadarm.yogiyo.ui.viewModel.RestaurantViewModel
import com.nadarm.yogiyo.util.subscribeMainThread
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_food_tab.*
import javax.inject.Inject

@ActivityScope
class FoodTabFragment @Inject constructor(
    private val provider: ViewModelProvider
) : BaseFragment() {

    private lateinit var binding: FragmentFoodTabBinding

    private val foodCategoryVm: FoodCategoryViewModel.ViewModelImpl by lazy {
        provider.get("foodCategoryVm", FoodCategoryViewModel.ViewModelImpl::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_tab, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val manager = fragmentManager!!
        val adapter = FoodCategoryPagerAdapter(manager)
        viewPager.adapter = adapter

        foodCategoryVm.outputs.foodCategoryList()
            .map {
                it.map { item ->
                    FoodTabItemFragment(
                        item as FoodCategory,
                        provider.get(RestaurantViewModel.ViewModelImpl::class.java)
                    )
                }
            }
            .subscribeMainThread(Schedulers.io(), compositeDisposable) { adapter.tabs = it }


        tabLayout.setupWithViewPager(viewPager)


        arguments?.let {
            tab_textView.text = it["category"].toString()
        }

        viewPager.currentItem

    }
}
