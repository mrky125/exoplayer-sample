package com.example.exoplayersample.epoxy

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel

class EpoxyController : TypedEpoxyController<EpoxyDataList>() {
    override fun buildModels(data: EpoxyDataList?) {
        data?.let {
            buildVertical1(it.vertical1)
            buildHorizontal1(it.horizontal1)
            buildVertical2(it.vertical2)
            buildHorizontal2(it.horizontal2)
            buildBottomMargin()
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

    private fun buildHorizontal1(list: List<String>) {
        carousel {
            id("carousel1")
            // left, top, right, bottom: padding on carousel overall
            // itemSpacingDp: horizontal space in each item
            padding(Carousel.Padding.dp(60, 16, 60, 16, 40))
            models(this@EpoxyController.createItemHorizontals("horizontal1", list))
        }
    }

    private fun createItemHorizontals(
        idPrefix: String,
        list: List<String>
    ): List<ItemHorizontalModel_> {
        return list.mapIndexed { i, str ->
            ItemHorizontalModel_().apply {
                id("$idPrefix$i")
                text(str)
            }
        }
    }

    private fun buildVertical2(list: List<String>) {
        list.forEachIndexed { i, str ->
            itemVertical {
                id("vertical2$i")
                text(str)
            }
        }
    }

    private fun buildHorizontal2(list: List<String>) {
        carousel {
            id("carousel2")
            // left, top, right, bottom: padding on carousel overall
            // itemSpacingDp: horizontal space in each item
            padding(Carousel.Padding.dp(60, 16, 60, 16, 40))
            models(this@EpoxyController.createItemHorizontals("horizontal2", list))
        }
    }

    private fun buildBottomMargin() {
        itemVertical {
            id("bottomMargin")
        }
    }
}

data class EpoxyDataList(
    val vertical1: List<String> = listOf("A", "B", "C", "D", "E", "F", "G"),
    val horizontal1: List<String> = listOf("H", "I", "J", "K", "L", "M", "N"),
    val vertical2: List<String> = listOf("O", "P", "Q", "R", "S", "T", "U"),
    val horizontal2: List<String> = listOf("V", "W", "X", "Y", "Z"),
)