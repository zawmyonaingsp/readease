package com.sevenpeaks.zawmyonaing.readease.presentation.login

import com.sevenpeaks.zawmyonaing.readease.utils.android.UiString

data class LoginScreenState(
    val email: String = "",
    val emailError: UiString? = null,
    val password: String = "",
    val passwordError: UiString? = null,
    val isLoading: Boolean = false,
) {
    val loginEnabled: Boolean = !isLoading &&
            email.isNotBlank() && emailError == null &&
            password.isNotBlank() && passwordError == null
}