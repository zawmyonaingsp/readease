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
import com.sevenpeaks.zawmyonaing.readease.presentation.articles.detail.ArticleDetailScreen
import com.sevenpeaks.zawmyonaing.readease.presentation.articles.detail.ArticleDetailViewModel

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