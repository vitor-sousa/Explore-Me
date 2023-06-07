package com.vitorsousa.data.model

import com.google.firebase.firestore.DocumentId
import com.vitorsousa.model.data.TouristSpot
import java.io.Serializable

data class TouristAttractionRemote(
    @DocumentId
    val id: String,
    val name: String,
    val imageUrl: String
) : Serializable {
    constructor() : this("", "", "")

    companion object {
        private const val serialVersionUID = 1L
    }
}

fun TouristAttractionRemote.toTouristSpot() = TouristSpot(id = id, name = name, imageUrl = imageUrl)
fun List<TouristAttractionRemote>.toTouristSpotList() = this.map { it.toTouristSpot() }
