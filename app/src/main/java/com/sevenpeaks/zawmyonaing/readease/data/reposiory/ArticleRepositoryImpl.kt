package com.sevenpeaks.zawmyonaing.readease.data.reposiory

import android.content.Context
import com.sevenpeaks.zawmyonaing.readease.domain.model.Article
import com.sevenpeaks.zawmyonaing.readease.domain.repository.ArticleRepository
import com.sevenpeaks.zawmyonaing.readease.utils.kotlin.CoroutineDispatcherProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val json: Json,
    private val dispatcherProvider: CoroutineDispatcherProvider,
) : ArticleRepository {

    private val articlesMutex = Mutex()
    private val articles = mutableListOf<Article>()


    override suspend fun getAllArticles(): List<Article> {
        return withContext(dispatcherProvider.io) { fetchArticles() }
    }

    override suspend fun getById(id: String): Article? {
        return getAllArticles().firstOrNull { it.id == id }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private suspend fun fetchArticles(): List<Article> {
        return articlesMutex.withLock {
            articles.ifEmpty {
                context.assets
                    .open("dummy_articles.json")
                    .use<InputStream, List<Article>>(json::decodeFromStream)
                    .also {
                        articles.clear()
                        articles.addAll(it)
                    }
            }
        }
    }
}