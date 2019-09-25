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
        fun getAdItemList(): LiveData<List<BaseItem>>
    }

    @Singleton
    class ViewModelImpl @Inject constructor(
        private val adRepository: AdRepository
    ) : BaseViewModel(), Inputs, Outputs {

        private val adListLiveData: MutableLiveData<List<Ad>> =
            MutableLiveData<List<Ad>>().apply {
                this.value = adRepository.getAds() }

        val inputs: Inputs = this
        val outputs: Outputs = this

//        override fun getAdItemList(): LiveData<List<BaseItem>> {
//            return Transformations.map(adListLiveData) { ads -> ads.map { AdItem(it) } }
//        }
        override fun getAdItemList(): LiveData<List<BaseItem>> {
            val result = Transformations.map(adListLiveData) { ads ->
                // TODO ads==null 값 안들어오는 이유 찾기
                println(ads)
                ads.map { AdItem(it) }
            }
            println(result.value)
            return result as LiveData<List<BaseItem>>
        }

        override fun itemClicked(item: BaseItem) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

}
