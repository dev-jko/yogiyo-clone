package com.nadarm.yogiyo.ui.viewModel

import com.nadarm.yogiyo.data.repository.FoodCategoryRepository
import com.nadarm.yogiyo.ui.model.BaseItem
import com.nadarm.yogiyo.ui.model.FoodCategoryItem
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

interface FoodCategoryViewModel {

    interface Inputs : BaseItem.Delegate

    interface Outputs {
        fun foodCategoryList(): Flowable<List<FoodCategoryItem>>
    }

    class ViewModelImpl @Inject constructor(
        private val foodCategoryRepo: FoodCategoryRepository
    ) : BaseViewModel(), Inputs, Outputs {

        private val itemClicked: PublishProcessor<BaseItem> = PublishProcessor.create()

        private val foodCategoryList: BehaviorProcessor<List<FoodCategoryItem>> =
            BehaviorProcessor.create()

        val inputs: Inputs = this
        val outputs: Outputs = this

        init {
            foodCategoryRepo.getCategories()
                .map { it.map { foodCategory -> FoodCategoryItem(foodCategory) } }
                .subscribeBy { foodCategoryList.onNext(it) }
                .addTo(compositeDisposable)

        }

        override fun foodCategoryList(): Flowable<List<FoodCategoryItem>> = foodCategoryList

        override fun itemClicked(item: BaseItem) {
            itemClicked.onNext(item)
        }
    }

}