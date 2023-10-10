package com.sevenpeaks.zawmyonaing.readease.domain.usecase.validations

import com.sevenpeaks.zawmyonaing.readease.R
import com.sevenpeaks.zawmyonaing.readease.utils.android.UiString

class ValidateName {

    private val specialCharRegex = "[^A-Za-z0-9 ]".toRegex()

    fun execute(name: String): ValidationResult {

        if (specialCharRegex.find(name) != null) {
            return ValidationResult(
                successful = false,
                errorMessage = UiString.from(R.string.msg_validation_name_special_char)
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}