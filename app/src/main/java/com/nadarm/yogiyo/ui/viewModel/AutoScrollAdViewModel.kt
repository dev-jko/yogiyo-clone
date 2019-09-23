package com.nadarm.yogiyo.ui.viewModel

import com.nadarm.yogiyo.data.repository.AdRepository
import com.nadarm.yogiyo.ui.model.BaseItem
import javax.inject.Inject
import javax.inject.Singleton


interface AutoScrollAdViewModel {

    interface Inputs : BaseItem.Delegate

    interface Outputs {

    }

    @Singleton
    class ViewModelImpl @Inject constructor(
        private val adRepository: AdRepository
    ) : BaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

        override fun itemClicked(item: BaseItem) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

}
