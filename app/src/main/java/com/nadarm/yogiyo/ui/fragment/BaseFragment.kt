package com.nadarm.yogiyo.ui.fragment

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }
}