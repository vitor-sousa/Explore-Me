package com.vitorsousa.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.vitorsousa.data.CoroutinesDispatchers
import com.vitorsousa.data.RemoteConstants.RIO_DE_JANEIRO
import com.vitorsousa.data.RemoteConstants.TOURIST_ATTRACTIONS
import com.vitorsousa.data.model.TouristAttractionRemote
import com.vitorsousa.data.model.toTouristSpotList
import com.vitorsousa.model.data.TouristSpot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Suppress("MagicNumber", "TooGenericExceptionCaught")
class FirestoreTouristSpotDataSource @Inject constructor(
    private val db: FirebaseFirestore,
    private val dispatchers: CoroutinesDispatchers
) : TouristSpotRepository {

    override fun getTopTouristSpots(language: String, limit: Int): Flow<List<TouristSpot>> =
        db.collection(TOURIST_ATTRACTIONS)
            .document(RIO_DE_JANEIRO)
            .collection(language)
            .limit(limit.toLong())
            .dataObjects<TouristAttractionRemote>().flowOn(dispatchers.io())
            .map { it.toTouristSpotList() }


}
