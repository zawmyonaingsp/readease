package com.sevenpeaks.zawmyonaing.readease.presentation.articles.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenpeaks.zawmyonaing.readease.analytics.AnalyticsManager
import com.sevenpeaks.zawmyonaing.readease.domain.repository.ArticleRepository
import com.sevenpeaks.zawmyonaing.readease.domain.repository.PreferenceRepository
import com.sevenpeaks.zawmyonaing.readease.utils.kotlin.CoroutineDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.consumed
import de.palm.composestateevents.triggered
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArticlesListViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val preferenceRepository: PreferenceRepository,
    private val dispatcherProvider: CoroutineDispatcherProvider,
) : ViewModel() {

    private val _screenState = MutableStateFlow(ArticleListScreenState())
    val screenState = _screenState.asStateFlow()

    private val _logoutCompletionStatus = MutableStateFlow<StateEvent>(consumed)
    val logoutCompletionStatus = _logoutCompletionStatus.asStateFlow()

    private val _premiumSubscribeStatus = MutableStateFlow<StateEvent>(consumed)
    val premiumSubscribeStatus = _premiumSubscribeStatus.asStateFlow()

    private val userFlow = preferenceRepository.user.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 2_000L),
        initialValue = null
    )

    var pendingArticle: ArticleItem? = null
        private set

    init {
        viewModelScope.launch {
            userFlow.collectLatest { user ->
                _screenState.update {
                    it.copy(isPremiumUser = user?.premiumSubscribed == true)
                }
            }
        }
        loadArticles()
    }

    private fun loadArticles() {
        if (_screenState.value.isLoading) {
            return
        }
        _screenState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val articles = articleRepository.getAllArticles()

            val articleItems = withContext(dispatcherProvider.default) {
                articles.map(ArticleItem.Companion::from).toPersistentList()
            }
            _screenState.update { it.copy(isLoading = false, articles = articleItems) }
        }
    }

    fun checkArticleAccess(article: ArticleItem): Boolean {
        val isPremiumUser = userFlow.value?.premiumSubscribed == true
        val hasAccess = isPremiumUser || !article.isPremium
        if (!hasAccess) {
            _screenState.update { it.copy(showSubscriptionDialog = true) }
            pendingArticle = article
        }
        return hasAccess
    }

    fun subscribeToPremium() {
        viewModelScope.launch {
            with(preferenceRepository) {
                getUser()?.let { setUser(it.copy(premiumSubscribed = true)) }
                _screenState.update { it.copy(showSubscriptionDialog = false) }
                _premiumSubscribeStatus.update { triggered }
            }
            AnalyticsManager.premiumSubscribe()
        }
    }

    fun unsubscribePremium() {
        viewModelScope.launch {
            with(preferenceRepository) {
                getUser()?.let { setUser(it.copy(premiumSubscribed = false)) }
                _screenState.update { it.copy(confirmCancelSubscription = false) }
            }
            AnalyticsManager.premiumUnsubscribe()
        }
    }

    fun onClickedSubscribeToPremium() {
        _screenState.update { it.copy(showSubscriptionDialog = true) }
    }

    fun onDismissSubscriptionDialog() {
        _screenState.update { it.copy(showSubscriptionDialog = false) }
    }

    fun onClickedUnsubscribe() {
        _screenState.update { it.copy(confirmCancelSubscription = true) }
    }

    fun onDismissUnsubscribeConfirmDialog() {
        _screenState.update { it.copy(confirmCancelSubscription = false) }
    }

    fun logout() {
        viewModelScope.launch {
            preferenceRepository.setUser(null)
            _logoutCompletionStatus.update { triggered }
            AnalyticsManager.signOut()
        }
    }

    fun onLogoutStatusConsumed() {
        _logoutCompletionStatus.update { consumed }
    }

    fun onPremiumSubscribeStatusConsumed() {
        _premiumSubscribeStatus.update { consumed }
    }
}