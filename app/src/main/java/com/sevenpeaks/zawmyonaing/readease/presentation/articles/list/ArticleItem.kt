package com.sevenpeaks.zawmyonaing.readease.presentation.articles.list

import android.os.Parcelable
import com.sevenpeaks.zawmyonaing.readease.domain.model.Article
import com.sevenpeaks.zawmyonaing.readease.utils.kotlin.DateTimeFormat
import com.sevenpeaks.zawmyonaing.readease.utils.kotlin.format
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleItem(
    val authorName: String = "",
    val publishedDate: String = "",
    val subtitle: String = "",
    val title: String = "",
    val id: String = "",
    val isPremium: Boolean = false,
    val category: String = "",
    val rating: Float = 0.0f,
): Parcelable {
    companion object {
        fun from(article: Article): ArticleItem = with(article) {
            val displayPublishedDate = publishedDate
                ?.format(DateTimeFormat.display_date_full_time)
                .orEmpty()

            ArticleItem(
                authorName = authorName,
                publishedDate = displayPublishedDate,
                subtitle = subtitle,
                title = title,
                id = id,
                isPremium = isPremium,
                category = category,
                rating = rating
            )
        }
    }
}