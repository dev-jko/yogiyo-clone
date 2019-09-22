package com.nadarm.yogiyo.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import com.nadarm.yogiyo.R
import com.nadarm.yogiyo.data.TestDataManager
import com.nadarm.yogiyo.data.cache.FoodCategoryCacheDataSource
import com.nadarm.yogiyo.databinding.FragmentMainFoodBinding
import com.nadarm.yogiyo.ui.adapter.AutoScrollCircularListAdapter
import com.nadarm.yogiyo.ui.adapter.CircularListAdapter
import com.nadarm.yogiyo.ui.adapter.MultiItemAdapter
import com.nadarm.yogiyo.ui.adapter.SingleItemListAdapter
import com.nadarm.yogiyo.ui.model.*
import kotlinx.android.synthetic.main.fragment_main_food.*

class MainFoodFragment : Fragment() {

    private lateinit var binding: FragmentMainFoodBinding

//    @Inject
//    lateinit var vm: MainFoodViewModel
//
//    @Inject
//    lateinit var mainAdapter: RecyclerView.Adapter<ViewHolder>
//
//    @Inject
//    lateinit var topAdAdapter: ListAdapter<BaseItem, ViewHolder>
//
//    @Inject
//    lateinit var menuAdapter: ListAdapter<BaseItem, ViewHolder>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_food, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mainAdapter = MultiItemAdapter()

        val topAdList = TestDataManager.topAds.map {
            AdItem(it)
        }
        val adAdapter = AutoScrollCircularListAdapter()
        val snapHelper = PagerSnapHelper()

        val menu = SingleItemListAdapter()
        val categoryList = FoodCategoryCacheDataSource().getCategories().map {
            FoodCategoryItem(it)
        }


//        binding.vm = vm
        binding.mainAdapter = mainAdapter
        mainAdapter.itemList = listOf(
            HorizontalListItem(topAdList, adAdapter, snapHelper),
            BaseItem.BlankItem,
            GridListItem(categoryList, menu),
            BaseItem.BlankItem
        )
    }
}
