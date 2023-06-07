package com.vitorsousa.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vitorsousa.home.R
import com.vitorsousa.model.data.City
import com.vitorsousa.model.data.TouristSpot


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {

    val touristSpotFeedState by viewModel.topTouristSpotFeedState.collectAsStateWithLifecycle()
    val uiState by viewModel.headerState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Header(headerUiState = uiState)
        TopSpotSightsFeed(topTouristSpotFeedState = touristSpotFeedState)
    }

}


@Composable
private fun Header(
    headerUiState: HeaderUiState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,
    ) {
        when (headerUiState) {
            is HeaderUiState.Loading -> {
                CircularProgressIndicator()
            }

            is HeaderUiState.Success -> {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = headerUiState.city.imageRes),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Text(
                    text = headerUiState.city.name.uppercase(),
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

    }
}

@Composable
fun TopSpotSightsFeed(
    topTouristSpotFeedState: TopTouristSpotFeedState,
    modifier: Modifier = Modifier
) {


    when (topTouristSpotFeedState) {
        is TopTouristSpotFeedState.Loading -> {
            Row(
                modifier = modifier
                    .padding(vertical = 32.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is TopTouristSpotFeedState.Success -> {
            AnimatedVisibility(visible = topTouristSpotFeedState.feed.isNotEmpty(), enter = fadeIn()) {
                Column(modifier = modifier.padding(vertical = 32.dp)) {
                    TopSpotTitle(
                        spotsSize = topTouristSpotFeedState.feed.size,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    SpotSightFeed(touristSpotList = topTouristSpotFeedState.feed)
                }
            }
        }
    }

}


@Composable
fun TopSpotTitle(
    spotsSize: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.top_sights, spotsSize),
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}


@Composable
fun SpotSightFeed(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    touristSpotList: List<TouristSpot>,
) {
    LazyRow(
        modifier = modifier.padding(top = 12.dp),
        state = lazyListState,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(
            items = touristSpotList,
            key = { it.id },
            itemContent = { item ->
                SpotSightItem(item)
            }
        )
    }
}

@Composable
fun SpotSightItem(
    touristSpot: TouristSpot,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            modifier = Modifier
                .height(200.dp)
                .width(140.dp)
                .clip(RoundedCornerShape(20.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(touristSpot.imageUrl)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            placeholder = painterResource(com.vitorsousa.model.R.drawable.img_rio_de_janeiro),
        )
        Text(
            text = touristSpot.name,
            modifier = Modifier
                .width(120.dp)
                .padding(top = 12.dp)
                .padding(horizontal = 8.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium.copy(
                shadow = Shadow(
                    color = Color.LightGray,
                    offset = Offset(2.0f, 2.0f),
                    blurRadius = 2f
                )
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
    }
}

sealed interface HeaderUiState {
    object Loading : HeaderUiState
    data class Success(val city: City) : HeaderUiState
}

sealed interface TopTouristSpotFeedState {
    object Loading : TopTouristSpotFeedState
    data class Success(val feed: List<TouristSpot>) : TopTouristSpotFeedState

}


//@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
//@Composable
//private fun HomeScreenPreview() {
//    HomeScreen()
//}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun SpotSightItemPreview() {
    SpotSightItem(
        touristSpot = TouristSpot(
            id = "1",
            name = "Cristo Redentor",
            imageUrl = ""
        )
    )
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun SpotSightFeedPreview() {
    SpotSightFeed(touristSpotList = TouristSpot.generateSampleList())
}
