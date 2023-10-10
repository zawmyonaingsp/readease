package com.sevenpeaks.zawmyonaing.readease.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sevenpeaks.zawmyonaing.readease.presentation.onboarding.OnboardingScreen
import timber.log.Timber

object OnboardingDestination : Destination(label = "onboarding")

fun NavGraphBuilder.onBoardingScreen(
    navHostController: NavHostController
) {
    composable(OnboardingDestination.route) {
        Timber.d("Navigating to Onboarding Screen")
        OnboardingScreen(
            onClickedSignUp = navHostController::navigateToSignUp,
            onClickedLogin = navHostController::navigateToLogin
        )
    }
}

fun NavHostController.navigateToOnboarding(
    navOptions: NavOptions? = null,
) = navigate(OnboardingDestination.route, navOptions)