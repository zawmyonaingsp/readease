package com.sevenpeaks.zawmyonaing.readease.presentation.articles.detail

import com.sevenpeaks.zawmyonaing.readease.domain.model.Article
import com.sevenpeaks.zawmyonaing.readease.utils.kotlin.DateTimeFormat
import com.sevenpeaks.zawmyonaing.readease.utils.kotlin.format


data class ArticleDetail(
    val authorName: String = "",
    val publishedDate: String = "",
    val subtitle: String = "",
    val title: String = "",
    val id: String = "",
    val isPremium: Boolean = false,
    val category: String = "",
    val rating: Float = 0.0f,
    val content: String = "",
) {
    companion object {
        fun from(article: Article): ArticleDetail = with(article) {
            val displayPublishedDate = publishedDate
                ?.format(DateTimeFormat.display_date_full_time)
                .orEmpty()
            ArticleDetail(
                authorName = authorName,
                publishedDate = displayPublishedDate,
                subtitle = subtitle,
                title = title,
                id = id,
                isPremium = isPremium,
                category = category,
                rating = rating,
                content = content,
            )
        }
    }
}