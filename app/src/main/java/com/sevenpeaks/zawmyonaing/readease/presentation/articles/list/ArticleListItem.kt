package com.sevenpeaks.zawmyonaing.readease.presentation.articles.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.sevenpeaks.zawmyonaing.readease.R
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingMedium
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingSmall
import com.sevenpeaks.zawmyonaing.readease.ui.widgets.ThemedPreview
import com.sevenpeaks.zawmyonaing.readease.utils.android.compose.preview.UiModePreviews

@Composable
fun ItemArticle(
    modifier: Modifier = Modifier,
    articleItem: ArticleItem,
    onClicked: (ArticleItem) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClicked(articleItem) }
    ) {

        Spacer(modifier = Modifier.height(spacingMedium))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacingMedium),
            text = articleItem.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(spacingSmall))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacingMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            RatingBar(
                value = articleItem.rating,
                style = RatingBarStyle.Fill(
                    activeColor = MaterialTheme.colorScheme.primary,
                    inActiveColor = MaterialTheme.colorScheme.primaryContainer
                ),
                size = 16.dp,
                spaceBetween = 6.dp,
                onValueChange = {},
                onRatingChanged = {}
            )

//            ComposeStars(
//                value = articleItem.rating,
//                numOfStars = 5,
//                size = 16.dp,
//                spaceBetween = 5.dp,
//                hideInactiveStars = false,
//                style = RatingBarStyle.Fill(
//                    activeColor = MaterialTheme.colorScheme.primary,
//                    inActiveColor = MaterialTheme.colorScheme.primaryContainer
//                ),
//                painterEmpty = rememberVectorPainter(image = Icons.Outlined.StarBorder),
//                painterFilled = rememberVectorPainter(image = Icons.Filled.Star)
//            )

            if (articleItem.isPremium) {
                Icon(
                    imageVector = Icons.Filled.WorkspacePremium,
                    contentDescription = stringResource(id = R.string.cd_premium_indicator),
                )
            }
        }

        Spacer(modifier = Modifier.height(spacingMedium))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacingMedium),
            text = articleItem.subtitle,
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.height(spacingSmall))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacingMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = articleItem.authorName,
                style = MaterialTheme.typography.labelMedium,
            )

            Text(
                text = articleItem.publishedDate,
                style = MaterialTheme.typography.labelMedium,
            )

        }

        Spacer(modifier = Modifier.height(spacingMedium))
    }

}

@UiModePreviews
@Composable
fun ItemArticlePreview() {
    ThemedPreview {
        ItemArticle(
            articleItem = ArticleItem(
                authorName = "Denise Woodard",
                publishedDate = "06 October 2023",
                subtitle = "Sample subtitle of this interesting article.Sample subtitle of this interesting article.Sample subtitle of this interesting article.",
                title = "Some catchy and interesting article title",
                id = "lacinia",
                isPremium = true,
                category = "Interesting",
                rating = 3.5f,
            ),
            onClicked = {}
        )
    }
}