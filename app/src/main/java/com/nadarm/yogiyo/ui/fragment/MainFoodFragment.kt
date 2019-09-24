package com.nadarm.yogiyo.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.PagerSnapHelper
import com.nadarm.yogiyo.R
import com.nadarm.yogiyo.data.cache.FoodCategoryCacheDataSource
import com.nadarm.yogiyo.databinding.FragmentMainFoodBinding
import com.nadarm.yogiyo.ui.adapter.AutoScrollCircularListAdapter
import com.nadarm.yogiyo.ui.adapter.BaseListAdapter
import com.nadarm.yogiyo.ui.model.*
import com.nadarm.yogiyo.ui.viewModel.AutoScrollAdViewModel
import javax.inject.Inject

class MainFoodFragment : BaseFragment() {

    private lateinit var binding: FragmentMainFoodBinding

//    @Inject
//    lateinit var vm: AutoScrollAdViewModel.ViewModelImpl
//
//    @Inject
//    lateinit var mainAdapter: RecyclerView.Adapter<ViewHolder>
//
//    @Inject
//    lateinit var topAdAdapter: ListAdapter<BaseItem, ViewHolder>
//
//    @Inject
//    lateinit var menuAdapter: ListAdapter<BaseItem, ViewHolder>

    @Inject
    lateinit var topAdVm: AutoScrollAdViewModel.ViewModelImpl


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_food, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mainAdapter = BaseListAdapter()

        val adAdapter = AutoScrollCircularListAdapter(delegate = object : BaseItem.Delegate {
            override fun itemClicked(item: BaseItem) {
                if (item is AdItem) {
                    Toast.makeText(
                        this@MainFoodFragment.context,
                        item.item.imageUrl,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        val snapHelper = PagerSnapHelper()

        val menu = BaseListAdapter()
        val categoryList = FoodCategoryCacheDataSource().getCategories().map {
            FoodCategoryItem(it)
        }


//        binding.vm = vm
        binding.mainAdapter = mainAdapter
        mainAdapter.submitList(
            listOf(
                HorizontalListItem(topAdVm.outputs.getAdList(), adAdapter, snapHelper),
                BaseItem.BlankItem,
                GridListItem(categoryList, menu),
                BaseItem.BlankItem
            )
        )
    }
}
