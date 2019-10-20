package com.nadarm.yogiyo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.nadarm.yogiyo.R
import com.nadarm.yogiyo.databinding.FragmentMainFoodBinding
import com.nadarm.yogiyo.di.ActivityScope
import com.nadarm.yogiyo.ui.adapter.BaseListAdapter
import com.nadarm.yogiyo.ui.listener.BaseScrollListener
import com.nadarm.yogiyo.ui.listener.ScrollStateListener
import com.nadarm.yogiyo.ui.model.*
import com.nadarm.yogiyo.ui.viewModel.AutoScrollAdViewModel
import com.nadarm.yogiyo.ui.viewModel.FoodCategoryViewModel
import com.nadarm.yogiyo.ui.viewModel.RestaurantViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class MainFoodFragment @Inject constructor(
    private val provider: ViewModelProvider
) : BaseFragment() {

    private lateinit var binding: FragmentMainFoodBinding

    private val topAdVm: AutoScrollAdViewModel.ViewModelImpl by lazy {
        provider.get("topAdVm", AutoScrollAdViewModel.ViewModelImpl::class.java)
    }
    private val foodCategoryVm: FoodCategoryViewModel.ViewModelImpl by lazy {
        provider.get(FoodCategoryViewModel.ViewModelImpl::class.java)
    }
    private val bottomAdVm: AutoScrollAdViewModel.ViewModelImpl by lazy {
        provider.get("bottomAdVm", AutoScrollAdViewModel.ViewModelImpl::class.java)
    }
    private val plusPopularVm: RestaurantViewModel.ViewModelImpl by lazy {
        provider.get("plusPopularVm", RestaurantViewModel.ViewModelImpl::class.java)
    }
    private val plusNewVm: RestaurantViewModel.ViewModelImpl by lazy {
        provider.get("plusNewVm", RestaurantViewModel.ViewModelImpl::class.java)
    }

    private val mainAdapter: BaseListAdapter = BaseListAdapter()
    private val topAdAdapter: BaseListAdapter by lazy {
        BaseListAdapter(delegate = topAdVm)
    }
    private val topAdScrollListener: BaseScrollListener by lazy {
        ScrollStateListener(topAdVm)
    }
    private val foodCategoryAdapter: BaseListAdapter by lazy {
        BaseListAdapter(delegate = foodCategoryVm)
    }
    private val bottomAdAdapter: BaseListAdapter by lazy {
        BaseListAdapter(delegate = bottomAdVm)
    }
    private val bottomAdScrollListener: BaseScrollListener by lazy {
        ScrollStateListener(bottomAdVm)
    }
    private val plusPopularAdapter: BaseListAdapter by lazy {
        BaseListAdapter(delegate = plusPopularVm)
    }
    private val plusNewAdapter: BaseListAdapter by lazy {
        BaseListAdapter(delegate = plusNewVm)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_food, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val topAdSnapHelper = PagerSnapHelper()
        val bottomAdSnapHelper = PagerSnapHelper()

        binding.mainAdapter = mainAdapter
        binding.mainRecyclerView.setItemViewCacheSize(10)

        mainAdapter.submitList(
            listOf(
                AutoScrollAdList(topAdAdapter, topAdSnapHelper, topAdScrollListener),
                BaseItem.BlankItem,
                GridList(foodCategoryAdapter),
                BaseItem.BlankItem,
                AutoScrollAdList(bottomAdAdapter, bottomAdSnapHelper, bottomAdScrollListener),
                BaseItem.BlankItem,
                PlusPopularRestaurantList(plusPopularAdapter),
                BaseItem.BlankItem,
                PlusNewRestaurantList(plusNewAdapter),
                BaseItem.BlankItem,
                BaseItem.BlankItem, BaseItem.BlankItem,
                BaseItem.BlankItem, BaseItem.BlankItem,
                BaseItem.BlankItem, BaseItem.BlankItem,
                BaseItem.BlankItem, BaseItem.BlankItem,
                BaseItem.BlankItem, BaseItem.BlankItem,
                BaseItem.BlankItem, BaseItem.BlankItem,
                BaseItem.BlankItem, BaseItem.BlankItem,
                BaseItem.BlankItem, BaseItem.BlankItem,
                BaseItem.BlankItem, BaseItem.BlankItem,
                BaseItem.BlankItem, BaseItem.BlankItem,
                BaseItem.BlankItem
            )
        )

        topAdVm.outputs.adItemList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                this.submitList(it, topAdAdapter)
            }
            .addTo(compositeDisposable)

        topAdVm.outputs.scrollPosition()
            .doOnNext { println("fragment = $it") }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { position ->
                topAdAdapter.getRecyclerView()?.scrollToPosition(position)
            }
            .addTo(compositeDisposable)

        topAdVm.outputs.smoothScrollPosition()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { position ->
                topAdAdapter.getRecyclerView()?.smoothScrollToPosition(position)
            }
            .addTo(compositeDisposable)

        foodCategoryVm.outputs.foodCategoryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                this.submitList(it, foodCategoryAdapter)
            }
            .addTo(compositeDisposable)

        bottomAdVm.outputs.adItemList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                this.submitList(it, bottomAdAdapter)
            }
            .addTo(compositeDisposable)

        bottomAdVm.outputs.scrollPosition()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { position ->
                bottomAdAdapter.getRecyclerView()?.scrollToPosition(position)
            }
            .addTo(compositeDisposable)

        bottomAdVm.outputs.smoothScrollPosition()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { position ->
                bottomAdAdapter.getRecyclerView()?.smoothScrollToPosition(position)
            }
            .addTo(compositeDisposable)

        plusPopularVm.outputs.restaurantList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                this.submitList(it, plusPopularAdapter)
            }
            .addTo(compositeDisposable)

        plusNewVm.outputs.restaurantList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                this.submitList(it, plusNewAdapter)
            }
            .addTo(compositeDisposable)

        topAdVm.inputs.setAdType(Ad.Type.Large)
        bottomAdVm.inputs.setAdType(Ad.Type.Small)

    }

    override fun onPause() {
        topAdAdapter.getRecyclerView()?.layoutManager?.let {
            if (it is LinearLayoutManager) {
                topAdVm.inputs.lastScrollPosition(it.findFirstCompletelyVisibleItemPosition())
            }
        }
        super.onPause()
    }

    private fun submitList(newList: List<BaseItem>, adapter: BaseListAdapter) {
        adapter.submitList(newList)
    }
}
