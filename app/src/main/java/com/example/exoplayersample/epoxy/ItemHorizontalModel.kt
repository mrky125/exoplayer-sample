package com.example.exoplayersample.epoxy

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.exoplayersample.BR
import com.example.exoplayersample.R

private const val LAYOUT_ID_ITEM_HORIZONTAL = R.layout.item_horizontal

@EpoxyModelClass(layout = LAYOUT_ID_ITEM_HORIZONTAL)
abstract class ItemHorizontalModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    var text: String = ""

    override fun getDefaultLayout(): Int {
        return LAYOUT_ID_ITEM_HORIZONTAL
    }

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.setVariable(BR.text, text)
    }
}