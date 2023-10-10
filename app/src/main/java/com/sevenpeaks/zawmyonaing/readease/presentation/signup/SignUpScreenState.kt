package com.sevenpeaks.zawmyonaing.readease.presentation.signup

import com.sevenpeaks.zawmyonaing.readease.utils.android.UiString

data class SignUpScreenState(
    val name: String = "",
    val nameError: UiString? = null,
    val email: String = "",
    val emailError: UiString? = null,
    val password: String = "",
    val passwordError: UiString? = null,
    val isLoading: Boolean = false,
) {
    val signUpEnabled: Boolean = !isLoading &&
            name.isNotBlank() && nameError == null &&
            email.isNotBlank() && emailError == null &&
            password.isNotBlank() && passwordError == null
}