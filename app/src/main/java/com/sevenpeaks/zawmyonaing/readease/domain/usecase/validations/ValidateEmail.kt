package com.sevenpeaks.zawmyonaing.readease.domain.usecase.validations

import android.util.Patterns
import com.sevenpeaks.zawmyonaing.readease.R
import com.sevenpeaks.zawmyonaing.readease.utils.android.UiString

class ValidateEmail {

    fun execute(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiString.from(R.string.hint_enter_email)
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiString.from(R.string.msg_invalid_email)
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}