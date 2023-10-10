package com.sevenpeaks.zawmyonaing.readease.domain.model

import com.sevenpeaks.zawmyonaing.readease.utils.kotlin.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Article(
    @SerialName("author_name")
    val authorName: String = "",
    @SerialName("category")
    val category: String = "",
    @SerialName("content")
    val content: String = "",
    @SerialName("published_date")
    @Serializable(with = DateSerializer::class)
    val publishedDate: Date?,
    @SerialName("subtitle")
    val subtitle: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("article_id")
    val id: String = UUID.randomUUID().toString(),
    @SerialName("is_premium")
    val isPremium: Boolean = false,
    @SerialName("rating")
    val rating: Float = 0.0f,
)