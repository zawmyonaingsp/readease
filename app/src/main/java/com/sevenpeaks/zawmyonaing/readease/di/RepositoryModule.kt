package com.sevenpeaks.zawmyonaing.readease.di

import com.sevenpeaks.zawmyonaing.readease.data.reposiory.ArticleRepositoryImpl
import com.sevenpeaks.zawmyonaing.readease.data.reposiory.PreferenceRepositoryImpl
import com.sevenpeaks.zawmyonaing.readease.domain.repository.ArticleRepository
import com.sevenpeaks.zawmyonaing.readease.domain.repository.PreferenceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPreferenceRepository(
        impl: PreferenceRepositoryImpl
    ): PreferenceRepository

    @Binds
    abstract fun bindArticleRepository(
        impl: ArticleRepositoryImpl
    ): ArticleRepository

} // Note on IDE showing as unused code : https://stackoverflow.com/a/68828417
