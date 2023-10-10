package com.sevenpeaks.zawmyonaing.readease.presentation.articles.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.sevenpeaks.zawmyonaing.readease.R
import com.sevenpeaks.zawmyonaing.readease.presentation.common.RatingDialog
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingExtraLarge
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingLarge
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingMedium
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingSmall
import com.sevenpeaks.zawmyonaing.readease.ui.widgets.ThemedPreview
import com.sevenpeaks.zawmyonaing.readease.utils.android.compose.preview.UiModePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    modifier: Modifier = Modifier,
    state: ArticleDetailScreenState,
    onRatingApplied: (Float) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.cd_back_to_articles_list
                            )
                        )
                    }
                }
            )
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            var showRatingDialog by remember { mutableStateOf(false) }

            if (showRatingDialog) {
                RatingDialog(
                    onRatingApplied = onRatingApplied,
                    onDismissRequest = { showRatingDialog = false }
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                ArticleDetailContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    detail = state.detail
                )

                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(
                            end = spacingExtraLarge,
                            bottom = spacingExtraLarge
                        ),
                    onClick = { showRatingDialog = true }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.StarBorder,
                        contentDescription = stringResource(id = R.string.cd_rate_article)
                    )
                }
            }
        }
    }
}

@Composable
private fun ArticleDetailContent(
    modifier: Modifier,
    detail: ArticleDetail,
) {
    Column(
        modifier = modifier.padding(horizontal = spacingMedium)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = detail.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(spacingSmall))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            RatingBar(
                value = detail.rating,
                style = RatingBarStyle.Fill(
                    activeColor = MaterialTheme.colorScheme.primary,
                    inActiveColor = MaterialTheme.colorScheme.primaryContainer
                ),
                size = 16.dp,
                spaceBetween = 6.dp,
                onValueChange = {},
                onRatingChanged = {}
            )

            if (detail.isPremium) {
                Icon(
                    imageVector = Icons.Filled.WorkspacePremium,
                    contentDescription = stringResource(id = R.string.cd_premium_indicator),
                )
            }
        }

        Spacer(modifier = Modifier.height(spacingMedium))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = detail.subtitle,
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.height(spacingSmall))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = detail.authorName,
                style = MaterialTheme.typography.labelMedium,
            )

            Text(
                text = detail.publishedDate,
                style = MaterialTheme.typography.labelMedium,
            )

        }

        Spacer(modifier = Modifier.height(spacingExtraLarge))

        Text(text = detail.content)

        Spacer(modifier = Modifier.height(spacingLarge))
    }
}

@UiModePreviews
@Composable
private fun ArticleDetailScreenPreview() {
    ThemedPreview {
        ArticleDetailScreen(
            state = ArticleDetailScreenState(
                detail = ArticleDetail(
                    authorName = "Alice Johnson",
                    publishedDate = "12th May 2020 4:23 PM",
                    subtitle = "Harnessing the Power of Quantum Bits",
                    title = "The Future of Quantum Computing",
                    id = "quis",
                    isPremium = true,
                    category = "Technology",
                    rating = 4.5f,
                    content = "Quantum computing promises to revolutionize computing as we know it. With the potential to solve complex problems exponentially faster than classical computers, it opens up new possibilities in fields such as cryptography, material science, and optimization."
                )
            ),
            onRatingApplied = {},
            onNavigateUp = {}
        )
    }
}