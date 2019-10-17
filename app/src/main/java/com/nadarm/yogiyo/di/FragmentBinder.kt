package com.nadarm.yogiyo.di

import com.nadarm.yogiyo.ui.fragment.MainFoodFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBinder {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainFoodFragment(): MainFoodFragment
}