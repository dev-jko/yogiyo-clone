package com.nadarm.yogiyo.ui.viewModel

import com.nadarm.yogiyo.data.repository.RestaurantRepository
import com.nadarm.yogiyo.ui.adapter.BaseListAdapter
import com.nadarm.yogiyo.ui.model.BaseItem
import com.nadarm.yogiyo.ui.model.Dish
import com.nadarm.yogiyo.ui.model.LabeledDishes
import com.nadarm.yogiyo.ui.model.Restaurant
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface RestaurantDetailViewModel {

    interface Inputs : BaseListAdapter.Delegate {
        fun restaurantId(id: Long)
    }

    interface Outputs {
        fun restaurantInfo(): Flowable<Restaurant>
        fun numOfMenu(): Flowable<Int>
        fun dishes(): Flowable<List<BaseItem>>
    }


    class ViewModelImpl @Inject constructor(
        private val restaurantRepo: RestaurantRepository
    ) : BaseViewModel(), Inputs, Outputs {

        private val restaurantId: PublishProcessor<Long> = PublishProcessor.create()
        private val dishItemClicked: PublishProcessor<Dish> = PublishProcessor.create()
        private val labelItemClicked: PublishProcessor<LabeledDishes> = PublishProcessor.create()

        private val restaurantInfo: BehaviorProcessor<Restaurant> = BehaviorProcessor.create()
        private val numOfMenu: BehaviorProcessor<Int> = BehaviorProcessor.create()
        private val dishes: BehaviorProcessor<List<BaseItem>> = BehaviorProcessor.create()

        val inputs: Inputs = this
        val outputs: Outputs = this

        init {

            val detail = restaurantId
                .flatMapSingle { id ->
                    restaurantRepo.getRestaurantDetail(id)
                        .subscribeOn(Schedulers.io())
                }

            detail
                .map { it.restaurant }
                .subscribe(restaurantInfo)

            detail
                .map { it.numOfMenu }
                .subscribe(numOfMenu)

            detail
                .map { it.labels }
                .subscribe(dishes)


        }


        override fun restaurantInfo(): Flowable<Restaurant> = restaurantInfo
        override fun numOfMenu(): Flowable<Int> = numOfMenu
        override fun dishes(): Flowable<List<BaseItem>> = dishes

        override fun itemClicked(item: BaseItem) {
            when (item) {
                is LabeledDishes -> labelItemClicked.onNext(item)
                is Dish -> dishItemClicked.onNext(item)
            }
        }

        override fun lastScrollPosition(position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun restaurantId(id: Long) {
            restaurantId.onNext(id)
        }
    }
}