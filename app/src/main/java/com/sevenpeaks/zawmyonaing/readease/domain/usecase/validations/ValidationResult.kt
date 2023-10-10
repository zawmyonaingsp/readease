package com.sevenpeaks.zawmyonaing.readease.domain.usecase.validations

import com.sevenpeaks.zawmyonaing.readease.utils.android.UiString

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiString? = null
)