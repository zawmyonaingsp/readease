package com.sevenpeaks.zawmyonaing.readease.domain.repository

import com.sevenpeaks.zawmyonaing.readease.domain.model.User
import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {

    val user: Flow<User?>

    suspend fun setUser(user: User?)

    suspend fun getUser(): User?

}