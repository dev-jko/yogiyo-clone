package com.nadarm.yogiyo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nadarm.yogiyo.R
import com.nadarm.yogiyo.databinding.FragmentRestaurantMenuItemBinding
import com.nadarm.yogiyo.ui.adapter.BaseListAdapter
import com.nadarm.yogiyo.ui.viewModel.RestaurantDetailViewModel
import com.nadarm.yogiyo.util.subscribeMainThread
import io.reactivex.schedulers.Schedulers

class RestaurantMenuItemFragment(
    private val restaurantDetailVm: RestaurantDetailViewModel.ViewModelImpl
) : BaseItemFragment() {

    private lateinit var binding: FragmentRestaurantMenuItemBinding
    private val adapter: BaseListAdapter = BaseListAdapter(restaurantDetailVm)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_restaurant_menu_item,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.adapter = adapter

        restaurantDetailVm.outputs.dishes()
            .subscribeMainThread(Schedulers.io(), compositeDisposable) {
                adapter.submitList(it)
            }


    }

    override fun getTitle(): String = "메뉴"
}