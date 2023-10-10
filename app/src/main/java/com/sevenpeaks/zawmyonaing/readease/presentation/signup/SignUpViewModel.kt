package com.sevenpeaks.zawmyonaing.readease.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenpeaks.zawmyonaing.readease.domain.model.User
import com.sevenpeaks.zawmyonaing.readease.domain.repository.PreferenceRepository
import com.sevenpeaks.zawmyonaing.readease.domain.usecase.validations.ValidateEmail
import com.sevenpeaks.zawmyonaing.readease.domain.usecase.validations.ValidateName
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
class SignUpViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
) : ViewModel() {

    private val validateEmail: ValidateEmail = ValidateEmail()
    private val validatePassword: ValidatePassword = ValidatePassword()
    private val validateName: ValidateName = ValidateName()

    private val _signUpScreenStateFlow = MutableStateFlow(SignUpScreenState())
    val signUpScreenStateFlow = _signUpScreenStateFlow.asStateFlow()

    private val _signUpSuccessEvent = MutableStateFlow<StateEvent>(consumed)
    val signUpSuccessEvent = _signUpSuccessEvent.asStateFlow()

    fun onNameChanged(name: String) {
        _signUpScreenStateFlow.update {
            it.copy(
                name = name,
                nameError = null
            )
        }
    }

    fun onEmailChanged(email: String) {
        _signUpScreenStateFlow.update {
            it.copy(
                email = email,
                emailError = null
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _signUpScreenStateFlow.update {
            it.copy(
                password = password,
                passwordError = null
            )
        }
    }

    fun onSignUpSuccessConsumed() {
        _signUpSuccessEvent.update { consumed }
    }

    fun onSignUp() {
        val state = _signUpScreenStateFlow.value
        val emailValidationResult = validateEmail.execute(state.email)
        val passwordValidationResult = validatePassword.execute(state.password)
        val nameValidationResult = validateName.execute(state.name)

        val hasError = listOf(
            emailValidationResult,
            passwordValidationResult,
            nameValidationResult
        ).any { !it.successful }

        if (hasError) {
            _signUpScreenStateFlow.update {
                it.copy(
                    emailError = emailValidationResult.errorMessage,
                    passwordError = passwordValidationResult.errorMessage,
                    nameError = nameValidationResult.errorMessage,
                    isLoading = false,
                )
            }
            return
        }

        _signUpScreenStateFlow.update { it.copy(isLoading = true) }
        submitData()


    }

    private fun submitData() {
        val state = _signUpScreenStateFlow.value
        viewModelScope.launch {
            val user = User(email = state.email)
            preferenceRepository.setUser(user)
            _signUpScreenStateFlow.update { it.copy(isLoading = false) }
            _signUpSuccessEvent.update { triggered }
        }
    }


}