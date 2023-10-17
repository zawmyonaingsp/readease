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
import com.sevenpeaks.zawmyonaing.readease.presentation.articles.list.ArticleListScreen
import com.sevenpeaks.zawmyonaing.readease.presentation.articles.list.ArticlesListViewModel
import de.palm.composestateevents.EventEffect

object ArticleListDestination : Destination(label = "articles_list")

fun NavGraphBuilder.articleListScreen(
    navHostController: NavHostController,
) {
    composable(route = ArticleListDestination.route) {

        val viewModel = hiltViewModel<ArticlesListViewModel>()
        val screenState by viewModel.screenState.collectAsStateWithLifecycle()
        val logoutStatus by viewModel.logoutCompletionStatus.collectAsStateWithLifecycle()
        val premiumSubscribeStatus by viewModel.premiumSubscribeStatus.collectAsStateWithLifecycle()

        EventEffect(
            event = logoutStatus,
            onConsumed = viewModel::onLogoutStatusConsumed,
            action = {
                navHostController.navigateToOnboarding(
                    navOptions = navOptions {
                        popUpTo(ROOT_NAV_GRAPH) { inclusive = true }
                    }
                )
            }
        )

        EventEffect(
            event = premiumSubscribeStatus,
            onConsumed = viewModel::onPremiumSubscribeStatusConsumed,
            action = {
                viewModel.pendingArticle?.let {
                    navHostController.navigateToArticleDetailScreen(it.id)
                }
            }
        )

        ArticleListScreen(
            state = screenState,
            onClickedLogout = viewModel::logout,
            onClickedArticleItem = { articleItem ->
                if (viewModel.checkArticleAccess(articleItem)) {
                    navHostController.navigateToArticleDetailScreen(articleItem.id)
                }
            },
            onPremiumSubscribe = viewModel::subscribeToPremium,
            onClickedSubscribeToPremium = viewModel::onClickedSubscribeToPremium,
            onDismissSubscriptionDialog = viewModel::onDismissSubscriptionDialog,
            onPremiumUnsubscribed = viewModel::unsubscribePremium,
            onClickedUnsubscribePremium = viewModel::onClickedUnsubscribe,
            onDismissUnsubscribeConfirmDialog = viewModel::onDismissUnsubscribeConfirmDialog
        )
    }
}


fun NavHostController.navigateToArticleListScreen(
    navOptions: NavOptions? = null
) = navigate(ArticleListDestination.route, navOptions)