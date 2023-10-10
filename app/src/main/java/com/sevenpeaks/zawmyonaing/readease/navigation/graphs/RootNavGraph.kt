package com.sevenpeaks.zawmyonaing.readease.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sevenpeaks.zawmyonaing.readease.navigation.Destination
import com.sevenpeaks.zawmyonaing.readease.navigation.OnboardingDestination
import com.sevenpeaks.zawmyonaing.readease.navigation.articleDetailScreen
import com.sevenpeaks.zawmyonaing.readease.navigation.articleListScreen
import com.sevenpeaks.zawmyonaing.readease.navigation.loginScreen
import com.sevenpeaks.zawmyonaing.readease.navigation.onBoardingScreen
import com.sevenpeaks.zawmyonaing.readease.navigation.signUpScreen

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    startDestination: Destination = OnboardingDestination,
    route: String = ROOT_NAV_GRAPH
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route,
        route = route
    ) {

        onBoardingScreen(navHostController)

        loginScreen(navHostController)

        signUpScreen(navHostController)

        articleListScreen(navHostController)

        articleDetailScreen(navHostController)

    }
}

const val ROOT_NAV_GRAPH = "RootNavGraph"