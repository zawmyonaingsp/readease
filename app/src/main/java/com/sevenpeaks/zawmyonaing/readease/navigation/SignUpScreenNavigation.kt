package com.sevenpeaks.zawmyonaing.readease.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.sevenpeaks.zawmyonaing.readease.navigation.graphs.ROOT_NAV_GRAPH
import com.sevenpeaks.zawmyonaing.readease.presentation.signup.SignUpScreen
import com.sevenpeaks.zawmyonaing.readease.presentation.signup.SignUpViewModel
import de.palm.composestateevents.EventEffect
import timber.log.Timber

object SignUpDestination : Destination(label = "sign_up")

fun NavGraphBuilder.signUpScreen(
    navHostController: NavHostController,
) {
    composable(SignUpDestination.route) {
        Timber.d("Navigating to Onboarding Screen")
        val viewModel: SignUpViewModel = hiltViewModel()
        val state by viewModel.signUpScreenStateFlow.collectAsStateWithLifecycle()
        val signUpSuccessEvent by viewModel.signUpSuccessEvent.collectAsStateWithLifecycle()

        EventEffect(
            event = signUpSuccessEvent,
            onConsumed = viewModel::onSignUpSuccessConsumed
        ) {
            navHostController.navigateToArticleListScreen(
                navOptions = navOptions {
                    popUpTo(ROOT_NAV_GRAPH) { inclusive = true }
                }
            )
        }

        SignUpScreen(
            signUpScreenState = state,
            onNameChanged = viewModel::onNameChanged,
            onEmailChanged = viewModel::onEmailChanged,
            onPasswordChanged = viewModel::onPasswordChanged,
            onClickedSignUp = viewModel::onSignUp,
        )

    }
}

fun NavHostController.navigateToSignUp(
    navOptions: NavOptions? = null,
) = navigate(SignUpDestination.route, navOptions)