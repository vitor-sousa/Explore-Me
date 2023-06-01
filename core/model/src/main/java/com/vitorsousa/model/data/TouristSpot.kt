package com.vitorsousa.model.data

import androidx.annotation.DrawableRes
import com.vitorsousa.model.R

data class TouristSpot(
    val id: String,
    val name: String,
    @DrawableRes
    val imageRes: Int = R.drawable.img_rio_de_janeiro  //TODO: Change Default Image
) {

    companion object {
        fun SampleList() = listOf(
            TouristSpot("1", "Cristo", R.drawable.img_rio_de_janeiro),
            TouristSpot("2", "Cristo", R.drawable.img_rio_de_janeiro),
            TouristSpot("3", "Cristo", R.drawable.img_rio_de_janeiro),
            TouristSpot("4", "Cristo", R.drawable.img_rio_de_janeiro),
            TouristSpot("5", "Cristo", R.drawable.img_rio_de_janeiro),
            TouristSpot("6", "Cristo", R.drawable.img_rio_de_janeiro),
            TouristSpot("7", "Cristo", R.drawable.img_rio_de_janeiro),
            TouristSpot("8", "Cristo", R.drawable.img_rio_de_janeiro),
            TouristSpot("9", "Cristo", R.drawable.img_rio_de_janeiro),
            TouristSpot("10", "Cristo", R.drawable.img_rio_de_janeiro),
        )
    }

}
