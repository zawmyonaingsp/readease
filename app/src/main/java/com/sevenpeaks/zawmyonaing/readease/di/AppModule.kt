package com.sevenpeaks.zawmyonaing.readease.di

import com.sevenpeaks.zawmyonaing.readease.utils.kotlin.CoroutineDispatcherProvider
import com.sevenpeaks.zawmyonaing.readease.utils.kotlin.StandardCoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcherProvider {
        return StandardCoroutineDispatcherProvider()
    }

    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = false
        isLenient = true
    }

}
