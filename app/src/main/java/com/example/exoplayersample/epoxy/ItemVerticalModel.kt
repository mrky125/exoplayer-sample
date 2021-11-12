package com.example.exoplayersample.epoxy

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.exoplayersample.BR
import com.example.exoplayersample.R

private const val LAYOUT_ID_ITEM_VERTICAL = R.layout.item_vertical

@EpoxyModelClass(layout = LAYOUT_ID_ITEM_VERTICAL)
abstract class ItemVerticalModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    var text: String = ""

    override fun getDefaultLayout(): Int {
        return LAYOUT_ID_ITEM_VERTICAL
    }

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.setVariable(BR.text, text)
    }
}