package com.sevenpeaks.zawmyonaing.readease.domain.repository

import com.sevenpeaks.zawmyonaing.readease.domain.model.Article

interface ArticleRepository {

    suspend fun getAllArticles(): List<Article>

    suspend fun getById(id: String): Article?

}