package com.sevenpeaks.zawmyonaing.readease.presentation.articles.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Unsubscribe
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.sevenpeaks.zawmyonaing.readease.R
import com.sevenpeaks.zawmyonaing.readease.analytics.compose.TrackScreenViewEvent
import com.sevenpeaks.zawmyonaing.readease.analytics.events.AppAnalytics
import com.sevenpeaks.zawmyonaing.readease.presentation.common.ConfirmUnsubscribe
import com.sevenpeaks.zawmyonaing.readease.presentation.common.SubscriptionDialog
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingMedium
import kotlinx.collections.immutable.PersistentList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListScreen(
    modifier: Modifier = Modifier,
    state: ArticleListScreenState,
    onClickedLogout: () -> Unit,
    onClickedArticleItem: (ArticleItem) -> Unit,
    onClickedSubscribeToPremium: () -> Unit,
    onPremiumSubscribe: () -> Unit,
    onDismissSubscriptionDialog: () -> Unit,
    onClickedUnsubscribePremium: () -> Unit,
    onPremiumUnsubscribed: () -> Unit,
    onDismissUnsubscribeConfirmDialog: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    TrackScreenViewEvent(name = AppAnalytics.EVENT_SCREEN_VIEW_ARTICLE_LIST)

    if (state.showSubscriptionDialog) {
        SubscriptionDialog(
            onDismissRequest = onDismissSubscriptionDialog,
            onSubscribe = onPremiumSubscribe,
        )
    }

    if (state.confirmCancelSubscription) {
        ConfirmUnsubscribe(
            onDismissRequest = onDismissUnsubscribeConfirmDialog,
            onUnsubscribe = onPremiumUnsubscribed
        )
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    IconButton(onClick = onClickedLogout) {
                        Icon(
                            imageVector = Icons.Filled.Logout,
                            contentDescription = stringResource(id = R.string.cd_logout),
                        )
                    }

                    if (!state.isPremiumUser) {
                        IconButton(onClick = onClickedSubscribeToPremium) {
                            Icon(
                                imageVector = Icons.Filled.WorkspacePremium,
                                contentDescription = stringResource(id = R.string.lbl_premium_subscription),
                            )
                        }
                    } else {
                        IconButton(onClick = onClickedUnsubscribePremium) {
                            Icon(
                                imageVector = Icons.Filled.Unsubscribe,
                                contentDescription = stringResource(id = R.string.lbl_premium_unsubscribe),
                            )
                        }
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                ArticleList(
                    modifier = Modifier.fillMaxSize(),
                    articles = state.articles,
                    onItemClicked = onClickedArticleItem
                )
            }
        }
    }
}


@Composable
private fun ArticleList(
    modifier: Modifier = Modifier,
    articles: PersistentList<ArticleItem>,
    onItemClicked: (ArticleItem) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(spacingMedium),
        verticalArrangement = Arrangement.spacedBy(spacingMedium)
    ) {
        items(
            items = articles,
            key = { it.id },
            contentType = { "ARTICLES" }
        ) { article ->
            ItemArticle(
                modifier = Modifier.fillMaxSize(),
                articleItem = article,
                onClicked = onItemClicked
            )
        }
    }
}
