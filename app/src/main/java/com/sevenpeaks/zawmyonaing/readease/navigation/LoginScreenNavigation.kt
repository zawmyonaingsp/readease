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
import com.sevenpeaks.zawmyonaing.readease.presentation.login.LoginScreen
import com.sevenpeaks.zawmyonaing.readease.presentation.login.LoginViewModel
import de.palm.composestateevents.EventEffect
import timber.log.Timber

object LoginDestination : Destination(label = "login")

fun NavGraphBuilder.loginScreen(
    navHostController: NavHostController,
) {
    composable(LoginDestination.route) {
        Timber.d("Navigating to Onboarding Screen")
        val viewModel: LoginViewModel = hiltViewModel()
        val state by viewModel.loginScreenStateFlow.collectAsStateWithLifecycle()
        val signUpSuccessEvent by viewModel.loginSuccessEvent.collectAsStateWithLifecycle()

        EventEffect(
            event = signUpSuccessEvent,
            onConsumed = viewModel::onLoginSuccessConsumed
        ) {
            navHostController.navigateToArticleListScreen(
                navOptions = navOptions {
                    popUpTo(ROOT_NAV_GRAPH) { inclusive = true }
                }
            )
        }

        LoginScreen(
            loginScreenState = state,
            onEmailChanged = viewModel::onEmailChanged,
            onPasswordChanged = viewModel::onPasswordChanged,
            onClickedLogin = viewModel::onLogin,
        )

    }
}

fun NavHostController.navigateToLogin(navOptions: NavOptions? = null) =
    navigate(LoginDestination.route, navOptions)