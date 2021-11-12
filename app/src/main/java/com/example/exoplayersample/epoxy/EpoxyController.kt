package com.example.exoplayersample.epoxy

import com.airbnb.epoxy.TypedEpoxyController

class EpoxyController : TypedEpoxyController<EpoxyDataList>() {
    override fun buildModels(data: EpoxyDataList?) {
        data?.let {
            buildVertical1(it.vertical1)
        }
    }

    private fun buildVertical1(list: List<String>) {
        list.forEachIndexed { i, str ->
            itemVertical {
                id("vertical1$i")
                text(str)
            }
        }
    }
}

data class EpoxyDataList(
    val vertical1: List<String> = listOf("A", "B", "C", "D", "E", "F", "G"),
    val horizontal1: List<String> = listOf("H", "I", "J", "K", "L", "M", "N"),
    val vertical2: List<String> = listOf("O", "P", "Q", "R", "S", "T", "U"),
    val horizontal2: List<String> = listOf("V", "W", "X", "Y", "Z"),
)