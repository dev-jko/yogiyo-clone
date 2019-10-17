package com.nadarm.yogiyo.ui.viewModel

import androidx.recyclerview.widget.RecyclerView
import com.nadarm.yogiyo.data.repository.AdRepository
import com.nadarm.yogiyo.ui.adapter.BaseListAdapter
import com.nadarm.yogiyo.ui.listener.ScrollStateListener
import com.nadarm.yogiyo.ui.model.Ad
import com.nadarm.yogiyo.ui.model.BaseItem
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.withLatestFrom
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


interface AutoScrollAdViewModel {

    interface Inputs : BaseListAdapter.Delegate, ScrollStateListener.Delegate {
        fun setAdType(type: Ad.Type)
    }

    interface Outputs {
        fun adItemList(): Flowable<List<BaseItem>>
        fun smoothScrollPosition(): Flowable<Int>
        fun scrollPosition(): Flowable<Int>
    }

    class ViewModelImpl @Inject constructor(
        private val adRepository: AdRepository
    ) : BaseViewModel(), Inputs, Outputs {

        init {
            println("auto scroll ad view model ${hashCode()}")
        }

        private val itemClicked: PublishProcessor<BaseItem> = PublishProcessor.create()
        private val adType: PublishProcessor<Ad.Type> = PublishProcessor.create()
        private val scrollStateChanged: PublishProcessor<Int> = PublishProcessor.create()
        private val scrollPositionChanged: PublishProcessor<Int> = PublishProcessor.create()
        private val listenerAdded: PublishProcessor<Unit> = PublishProcessor.create()

        private val adItemList: BehaviorProcessor<List<BaseItem>> = BehaviorProcessor.create()
        private val scrollPosition: BehaviorProcessor<Int> = BehaviorProcessor.createDefault(1)
        private val smoothScrollPosition: BehaviorProcessor<Int> = BehaviorProcessor.create()

        val inputs: Inputs = this
        val outputs: Outputs = this

        init {

            adType
                .flatMapSingle { type ->
                    adRepository.getAds(type)
                        .subscribeOn(Schedulers.io())
                }
                .map { it.toMutableList() }
                .map { list ->
                    if (list.size > 1) {
                        list.add(0, list.last())
                        list.add(list[1])
                    }
                    list
                }
                .subscribe { adItemList.onNext(it) }
                .addTo(compositeDisposable)


            val itemCount = adItemList.map { it.size }

            listenerAdded
                .withLatestFrom(scrollPositionChanged) { _, position -> position }
                .subscribe(scrollPosition)

            scrollPositionChanged
                .withLatestFrom(itemCount) { position, count -> position to count }
                .filter { it.second > 1 }
                .filter { it.first == it.second - 1 || it.first == 0 }
                .map {
                    val position = it.first
                    val count = it.second
                    when (position) {
                        count - 1 -> 1
                        0 -> count - 2
                        else -> position
                    }
                }
                .subscribe(scrollPosition)

            Flowable
                .interval(3000, TimeUnit.MILLISECONDS)
                .withLatestFrom(scrollStateChanged) { _, state -> state }
                .filter { state -> state == RecyclerView.SCROLL_STATE_IDLE }
                .withLatestFrom(scrollPositionChanged) { _, position -> position + 1 }
                .subscribe(smoothScrollPosition)

            scrollStateChanged(RecyclerView.SCROLL_STATE_IDLE)

        }

        override fun adItemList(): Flowable<List<BaseItem>> = adItemList
        override fun scrollPosition(): Flowable<Int> = scrollPosition
        override fun smoothScrollPosition(): Flowable<Int> = smoothScrollPosition

        override fun itemClicked(item: BaseItem) {
            itemClicked.onNext(item)
        }

        override fun setAdType(type: Ad.Type) {
            adType.onNext(type)
        }

        override fun scrollStateChanged(state: Int) {
            scrollStateChanged.onNext(state)
        }

        override fun scrollPositionChanged(position: Int) {
            scrollPositionChanged.onNext(position)
        }

        override fun listenerAdded() {
            listenerAdded.onNext(Unit)
        }
    }

}
