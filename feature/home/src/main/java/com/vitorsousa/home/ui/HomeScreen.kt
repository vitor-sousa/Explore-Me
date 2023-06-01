package com.vitorsousa.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vitorsousa.home.R
import com.vitorsousa.home.data.City
import com.vitorsousa.home.data.TouristSpot

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val uiState = viewModel.state.value

    when (uiState) {
        is HomeViewModel.UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is HomeViewModel.UiState.Success -> {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Header(city = uiState.city)
                TopSpotSights(uiState.touristSpotList)
            }
        }
    }

}


@Composable
private fun Header(
    city: City,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = city.imageRes),
            contentScale = ContentScale.Crop,
            contentDescription = "image" // TODO: Write a correct description
        )
        Text(
            text = city.name.uppercase(),
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
fun TopSpotSights(
    touristSpotList: List<TouristSpot>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 32.dp)) {
        TopSpotTitle(
            spotsSize = touristSpotList.size,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        SpotSightFeed(touristSpotList = touristSpotList)
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
    touristSpotList: List<TouristSpot>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(items = touristSpotList, itemContent = { item ->
            SpotSightItem(item)
        })
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
        Image(
            modifier = Modifier
                .height(200.dp)
                .width(140.dp)
                .clip(RoundedCornerShape(10.dp)),
            painter = painterResource(id = touristSpot.imageRes),
            contentScale = ContentScale.Crop,
            contentDescription = "image", // TODO: Write a correct description
        )
        Text(
            text = touristSpot.name,
            modifier = Modifier
                .width(120.dp)
                .padding(top = 24.dp)
                .padding(horizontal = 8.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun SpotSightItemPreview() {
    SpotSightItem(
        TouristSpot(
            id = "1",
            name ="Cristo Redentor"
        )
    )
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun SpotSightFeedPreview() {
    SpotSightFeed(touristSpotList = TouristSpot.SampleList())
}