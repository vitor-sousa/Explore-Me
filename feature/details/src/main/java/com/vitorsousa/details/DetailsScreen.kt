package com.vitorsousa.details

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vitorsousa.model.data.TouristSpot

@Suppress("MaxLineLength")
@Composable
fun DetailsScreen(
    touristSpotId: String?
) {
//    if (touristSpotId != null) {
//
//        Column {
//            Header(
//                headerUiState = HeaderUiState.Success(touristSpot)
//            )
//        }
//
//    }
}

@Composable
fun Header(
    headerUiState: HeaderUiState
) {

    when (headerUiState) {
        is HeaderUiState.Loading -> {
            CircularProgressIndicator()
        }

        is HeaderUiState.Success -> {
            AsyncImage(
                modifier = Modifier
                    .height(200.dp)
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(headerUiState.touristSpot.imageUrl)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
    }

}


sealed interface HeaderUiState {
    object Loading : HeaderUiState
    data class Success(val touristSpot: TouristSpot) : HeaderUiState
}



@Preview
@Composable
fun HeaderPreview() {
    Header(
        headerUiState = HeaderUiState.Success(
            TouristSpot(
                "1", "Cristo", ""
            )
        )
    )
}
