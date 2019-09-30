package com.nadarm.yogiyo.ui.viewModel

import com.nadarm.yogiyo.data.repository.AdRepository
import com.nadarm.yogiyo.ui.model.AdItem
import com.nadarm.yogiyo.ui.model.BaseItem
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


interface AutoScrollAdViewModel {

    interface Inputs : BaseItem.Delegate {
        fun setAdType(type: Int)
    }

    interface Outputs {
        fun adItemList(): Flowable<List<BaseItem>>
    }

    class ViewModelImpl @Inject constructor(
        private val adRepository: AdRepository
    ) : BaseViewModel(), Inputs, Outputs {

        private val itemClicked: PublishProcessor<BaseItem> = PublishProcessor.create()
        private val adType: PublishProcessor<Int> = PublishProcessor.create()

        private val adItemList: BehaviorProcessor<List<BaseItem>> = BehaviorProcessor.create()

        val inputs: Inputs = this
        val outputs: Outputs = this

        init {

            adType.flatMapSingle { type ->
                adRepository.getAds(type)
                    .map { list ->
                        list.map { ad -> AdItem(ad) as BaseItem }
                    }.subscribeOn(Schedulers.io())
            }
                .subscribe { adItemList.onNext(it) }
                .addTo(compositeDisposable)


        }

        override fun adItemList(): Flowable<List<BaseItem>> = adItemList

        override fun itemClicked(item: BaseItem) {
            itemClicked.onNext(item)
        }

        override fun setAdType(type: Int) {
            adType.onNext(type)
        }
    }

}
