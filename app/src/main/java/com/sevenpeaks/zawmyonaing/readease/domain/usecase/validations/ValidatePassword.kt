package com.sevenpeaks.zawmyonaing.readease.domain.usecase.validations

import com.sevenpeaks.zawmyonaing.readease.R
import com.sevenpeaks.zawmyonaing.readease.utils.android.UiString

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (password.length < MIN_PASSWORD_LENGTH) {
            return ValidationResult(
                successful = false,
                errorMessage = UiString.from(
                    R.string.msg_validation_password_length,
                    MIN_PASSWORD_LENGTH
                )
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = UiString.from(R.string.msg_validation_password_char)
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 4
    }
}