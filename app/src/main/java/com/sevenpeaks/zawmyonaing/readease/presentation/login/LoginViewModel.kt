package com.sevenpeaks.zawmyonaing.readease.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenpeaks.zawmyonaing.readease.domain.model.User
import com.sevenpeaks.zawmyonaing.readease.domain.repository.PreferenceRepository
import com.sevenpeaks.zawmyonaing.readease.domain.usecase.validations.ValidateEmail
import com.sevenpeaks.zawmyonaing.readease.domain.usecase.validations.ValidatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.consumed
import de.palm.composestateevents.triggered
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
) : ViewModel() {

    private val validatePassword: ValidatePassword = ValidatePassword()
    private val validateEmail: ValidateEmail = ValidateEmail()

    private val _loginScreenStateFlow = MutableStateFlow(LoginScreenState())
    val loginScreenStateFlow = _loginScreenStateFlow.asStateFlow()

    private val _loginSuccessEvent = MutableStateFlow<StateEvent>(consumed)
    val loginSuccessEvent = _loginSuccessEvent.asStateFlow()

    fun onEmailChanged(email: String) {
        _loginScreenStateFlow.update {
            it.copy(
                email = email,
                emailError = null
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _loginScreenStateFlow.update {
            it.copy(
                password = password,
                passwordError = null
            )
        }
    }

    fun onLoginSuccessConsumed() {
        _loginSuccessEvent.update { consumed }
    }

    fun onLogin() {
        val state = _loginScreenStateFlow.value
        val emailValidationResult = validateEmail.execute(state.email)
        val passwordValidationResult = validatePassword.execute(state.password)

        val hasError = listOf(
            emailValidationResult,
            passwordValidationResult,
        ).any { !it.successful }

        if (hasError) {
            _loginScreenStateFlow.update {
                it.copy(
                    emailError = emailValidationResult.errorMessage,
                    passwordError = passwordValidationResult.errorMessage,
                    isLoading = false,
                )
            }
            return
        }

        _loginScreenStateFlow.update { it.copy(isLoading = true) }
        submitData()

    }

    private fun submitData() {
        val state = _loginScreenStateFlow.value
        viewModelScope.launch {
            val user = User(email = state.email)
            preferenceRepository.setUser(user)
            _loginScreenStateFlow.update { it.copy(isLoading = false) }
            _loginSuccessEvent.update { triggered }
        }
    }
}