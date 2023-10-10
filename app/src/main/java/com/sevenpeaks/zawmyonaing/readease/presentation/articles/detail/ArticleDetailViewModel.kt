package com.sevenpeaks.zawmyonaing.readease.presentation.articles.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenpeaks.zawmyonaing.readease.domain.repository.ArticleRepository
import com.sevenpeaks.zawmyonaing.readease.navigation.ARG_ARTICLE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    saveStateHandle: SavedStateHandle,
    private val articleRepository: ArticleRepository,
) : ViewModel() {

    private val _screenState = MutableStateFlow(ArticleDetailScreenState())
    val screenState = _screenState.asStateFlow()

    init {
        loadArticleDetail(articleId = saveStateHandle[ARG_ARTICLE_ID] ?: "")
    }

    private fun loadArticleDetail(articleId: String) {
        _screenState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val detail = articleRepository
                .getById(articleId)?.let { ArticleDetail.from(it) }
                ?: ArticleDetail()
            _screenState.update { it.copy(isLoading = false, detail = detail) }
        }
    }
}