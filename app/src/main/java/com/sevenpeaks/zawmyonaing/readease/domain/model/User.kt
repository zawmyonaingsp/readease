package com.sevenpeaks.zawmyonaing.readease.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val premiumSubscribed: Boolean = false,
)