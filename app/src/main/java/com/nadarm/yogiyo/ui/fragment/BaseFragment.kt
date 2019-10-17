package com.nadarm.yogiyo.ui.fragment

import android.os.Bundle
import com.nadarm.yogiyo.ui.viewModel.BaseViewModel
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

//    @Inject lateinit var stateVm:BaseViewModel

    protected val compositeDisposable = CompositeDisposable()

    override fun onDetach() {
        super.onDetach()
        compositeDisposable.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }
}