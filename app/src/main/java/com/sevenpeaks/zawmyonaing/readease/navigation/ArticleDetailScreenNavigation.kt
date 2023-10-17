package com.sevenpeaks.zawmyonaing.readease.navigation

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sevenpeaks.zawmyonaing.readease.R
import com.sevenpeaks.zawmyonaing.readease.analytics.AnalyticsManager
import com.sevenpeaks.zawmyonaing.readease.analytics.events.AppAnalytics
import com.sevenpeaks.zawmyonaing.readease.presentation.articles.detail.ArticleDetailScreen
import com.sevenpeaks.zawmyonaing.readease.presentation.articles.detail.ArticleDetailViewModel
import timber.log.Timber

object ArticleDetailDestination : Destination(
    label = "article_detail",
    arguments = arrayOf(ARG_ARTICLE_ID)
) {

    fun getAddress(articleId: String): String {
        return address(args = mapOf(ARG_ARTICLE_ID to articleId))
    }

}

fun NavGraphBuilder.articleDetailScreen(
    navHostController: NavHostController,
) {
    composable(
        route = ArticleDetailDestination.route,
        arguments = listOf(
            navArgument(ARG_ARTICLE_ID) {
                type = NavType.StringType
                nullable = false
            }
        )
    ) {
        val viewModel: ArticleDetailViewModel = hiltViewModel()
        val screenState by viewModel.screenState.collectAsStateWithLifecycle()
        val context = LocalContext.current

        ArticleDetailScreen(
            state = screenState,
            onRatingApplied = { rating ->
                trackRatingAction(rating,screenState.detail.id)
                Toast.makeText(
                    context,
                    context.getString(R.string.msg_rating_applied, rating),
                    Toast.LENGTH_SHORT
                ).show()
            },
            onNavigateUp = navHostController::popBackStack
        )
    }
}

private fun trackRatingAction(rating: Float, articleId: String) {
    Timber.d("trackRatingAction() called with: rating = [$rating], articleId = [$articleId]")
    AnalyticsManager.incrementUserProperty(
        name = AppAnalytics.USER_LIFETIME_ARTICLE_RATES_COUNT,
        value = 1.0
    )
    AnalyticsManager.logEvent(
        eventName = AppAnalytics.ACTION_RATE_ARTICLE,
        params = mapOf(
            AppAnalytics.PARAM_ARTICLE_ID to articleId,
            AppAnalytics.PARAM_ARTICLE_RATING to rating,
        )
    )
}

fun NavHostController.navigateToArticleDetailScreen(
    articleId: String,
    navOptions: NavOptions? = null
) {
    navigate(
        ArticleDetailDestination.getAddress(articleId),
        navOptions = navOptions
    )
}

const val ARG_ARTICLE_ID = "id"