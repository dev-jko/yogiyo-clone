package com.nadarm.yogiyo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nadarm.yogiyo.R
import com.nadarm.yogiyo.databinding.FragmentBottomSheetDialogBinding
import com.nadarm.yogiyo.ui.model.Dish

class OrderBottomSheetDialogFragment constructor(
    private val dish: Dish
) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bottom_sheet_dialog,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.item = dish
    }
}