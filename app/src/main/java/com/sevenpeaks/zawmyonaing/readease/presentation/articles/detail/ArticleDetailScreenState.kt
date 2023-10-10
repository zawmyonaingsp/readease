package com.sevenpeaks.zawmyonaing.readease.presentation.articles.detail

data class ArticleDetailScreenState(
    val detail: ArticleDetail = ArticleDetail(),
    val isLoading: Boolean = false,
)