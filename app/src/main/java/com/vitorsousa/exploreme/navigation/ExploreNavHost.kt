package com.vitorsousa.exploreme.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vitorsousa.details.DetailsScreen
import com.vitorsousa.home.ui.HomeScreen


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ExploreNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) {
            HomeScreen(
                onTouristSpotClicked = { touristSpot ->
                    navController.navigateToDetails(touristSpot.id)
                }
            )
        }
        composable(
            route = Details.routeWithArgs,
            arguments = Details.arguments
        ) { navBackStackEntry ->
            val touristSpotId: String? =
                navBackStackEntry.arguments?.getString(Details.detailsTypeArgs)
            DetailsScreen(touristSpotId = touristSpotId)
        }
    }

}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

private fun NavHostController.navigateToDetails(touristSpotId: String) {
    this.navigateSingleTopTo("${Details.route}/$touristSpotId")
}

