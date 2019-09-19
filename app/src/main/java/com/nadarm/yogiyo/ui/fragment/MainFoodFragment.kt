package com.nadarm.yogiyo.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nadarm.yogiyo.R
import com.nadarm.yogiyo.databinding.FragmentMainFoodBinding
import com.nadarm.yogiyo.ui.adapter.ViewHolder
import com.nadarm.yogiyo.ui.model.BaseItem
import com.nadarm.yogiyo.ui.viewModel.MainFoodViewModel
import javax.inject.Inject

class MainFoodFragment : Fragment() {

    private lateinit var binding: FragmentMainFoodBinding

    @Inject
    lateinit var vm: MainFoodViewModel

    @Inject
    lateinit var mainAdapter: RecyclerView.Adapter<ViewHolder>

    @Inject
    lateinit var topAdAdapter: ListAdapter<BaseItem, ViewHolder>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_food, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.vm = vm
        binding.mainAdapter = mainAdapter
    }
}
