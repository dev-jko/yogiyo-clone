package com.nadarm.yogiyo.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.nadarm.yogiyo.data.repository.AdRepository
import com.nadarm.yogiyo.ui.model.Ad
import com.nadarm.yogiyo.ui.model.AdItem
import com.nadarm.yogiyo.ui.model.BaseItem
import javax.inject.Inject
import javax.inject.Singleton


interface AutoScrollAdViewModel {

    interface Inputs : BaseItem.Delegate

    interface Outputs {
        fun getAdList(): LiveData<List<AdItem>>
    }

    @Singleton
    class ViewModelImpl @Inject constructor(
        private val adRepository: AdRepository
    ) : BaseViewModel(), Inputs, Outputs {

        private val adListLiveData: MutableLiveData<List<Ad>> =
            MutableLiveData<List<Ad>>().also { it.postValue(adRepository.getAds()) }

        val inputs: Inputs = this
        val outputs: Outputs = this

        override fun getAdList(): LiveData<List<AdItem>> {
            Transformations.map(adListLiveData) { ads -> ads.map { AdItem(it) } }
        }

        override fun itemClicked(item: BaseItem) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

}
