package com.vitorsousa.exploreme.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val route: String
}

object Home: Destination {
    override val route: String = "home"
}

object Details: Destination {
    override val route: String = "details"
    const val detailsTypeArgs = "tourist_spot"
    val routeWithArgs = "$route/{$detailsTypeArgs}"
    val arguments = listOf(
        navArgument(detailsTypeArgs) { type = NavType.StringType }
    )
}
