package com.vitorsousa.model.data

import java.io.Serializable

data class TouristSpot(
    val id: String,
    val name: String,
    val imageUrl: String
): Serializable {

    companion object {
        private const val serialVersionUID = 1L
        fun generateSampleList() = listOf(
            TouristSpot("1", "Cristo", "https://cdn.limber.net.br/img/bilhetes/3073/3073-CFwXwozTS6.jpeg"),
            TouristSpot("2", "Cristo", "https://cdn.limber.net.br/img/bilhetes/3073/3073-CFwXwozTS6.jpeg"),
            TouristSpot("3", "Cristo", "https://cdn.limber.net.br/img/bilhetes/3073/3073-CFwXwozTS6.jpeg"),
            TouristSpot("4", "Cristo", "https://cdn.limber.net.br/img/bilhetes/3073/3073-CFwXwozTS6.jpeg"),
            TouristSpot("5", "Cristo", "https://cdn.limber.net.br/img/bilhetes/3073/3073-CFwXwozTS6.jpeg"),
            TouristSpot("6", "Cristo", "https://cdn.limber.net.br/img/bilhetes/3073/3073-CFwXwozTS6.jpeg"),
            TouristSpot("7", "Cristo", "https://cdn.limber.net.br/img/bilhetes/3073/3073-CFwXwozTS6.jpeg"),
            TouristSpot("8", "Cristo", "https://cdn.limber.net.br/img/bilhetes/3073/3073-CFwXwozTS6.jpeg"),
            TouristSpot("9", "Cristo", "https://cdn.limber.net.br/img/bilhetes/3073/3073-CFwXwozTS6.jpeg"),
            TouristSpot("10", "Cristo", "https://cdn.limber.net.br/img/bilhetes/3073/3073-CFwXwozTS6.jpeg"),
        )
    }

}
