package com.nadarm.yogiyo.di

import com.nadarm.yogiyo.data.DataBindingModule
import dagger.Component

@Component(modules = [DataBindingModule::class])
interface AppComponent {

}