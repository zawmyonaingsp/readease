package com.sevenpeaks.zawmyonaing.readease.presentation.articles.list

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class ArticleListScreenState(
    val articles: PersistentList<ArticleItem> = persistentListOf(),
    val isLoading: Boolean = false,
    val isPremiumUser: Boolean = false,
    val showSubscriptionDialog: Boolean = false,
)
