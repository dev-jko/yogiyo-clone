package com.nadarm.yogiyo.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nadarm.yogiyo.R
import com.nadarm.yogiyo.ui.adapter.BaseListAdapter
import com.nadarm.yogiyo.ui.model.BaseItem
import com.nadarm.yogiyo.ui.model.FoodCategory
import com.nadarm.yogiyo.ui.viewModel.RestaurantViewModel
import com.nadarm.yogiyo.util.subscribeMainThread
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_food_tab_item.*


class FoodTabItemFragment(
    private val category: FoodCategory,
    private val restaurantVm: RestaurantViewModel.ViewModelImpl
) : BaseFragment() {

    private val adapter: BaseListAdapter = BaseListAdapter(restaurantVm)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_tab_item, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        restaurantsRecyclerView.adapter = adapter

        restaurantVm.outputs.restaurantList()
            .map { it.toMutableList() }
            .map {
                it.apply {
                    add(0, BaseItem.BlankItem)
                    add(BaseItem.BlankItem)
                }
            }
            .subscribeMainThread(Schedulers.io(), compositeDisposable) {
                adapter.submitList(it)
            }

        restaurantVm.inputs.isPlus(false)
        restaurantVm.inputs.category(category.id)
    }

    fun getCategory(): FoodCategory = category
}
