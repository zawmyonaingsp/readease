package com.sevenpeaks.zawmyonaing.readease.presentation.signup

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.sevenpeaks.zawmyonaing.readease.R
import com.sevenpeaks.zawmyonaing.readease.presentation.common.ScreenHeroText
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingExtraLarge
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingMedium
import com.sevenpeaks.zawmyonaing.readease.ui.widgets.ThemedPreview
import com.sevenpeaks.zawmyonaing.readease.utils.android.compose.preview.UiModePreviews

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    signUpScreenState: SignUpScreenState,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onClickedSignUp: () -> Unit,
) {
    SignUpUI(
        modifier = modifier.fillMaxSize(),
        name = signUpScreenState.name,
        nameError = signUpScreenState.nameError?.asString(),
        onNameChanged = onNameChanged,
        email = signUpScreenState.email,
        emailError = signUpScreenState.emailError?.asString(),
        onEmailChanged = onEmailChanged,
        password = signUpScreenState.password,
        passwordError = signUpScreenState.passwordError?.asString(),
        onPasswordChanged = onPasswordChanged,
        onClickedSignUp = onClickedSignUp,
        signUpEnabled = signUpScreenState.signUpEnabled
    )
}

@Composable
private fun SignUpUI(
    modifier: Modifier,
    name: String,
    nameError: String? = null,
    email: String,
    emailError: String? = null,
    password: String,
    passwordError: String? = null,
    signUpEnabled: Boolean = false,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onClickedSignUp: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {

        ScreenHeroText(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            message = stringResource(id = R.string.lbl_welcome_onboarding)
        )

        Spacer(modifier = Modifier.height(spacingExtraLarge))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = spacingExtraLarge,
                    vertical = spacingMedium
                ),
            value = name,
            onValueChange = onNameChanged,
            isError = !nameError.isNullOrBlank(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                autoCorrect = false,
            ),
            placeholder = {
                Text(text = stringResource(id = R.string.hint_enter_name))
            },
            singleLine = true,
            supportingText = if (nameError.isNullOrBlank()) {
                null
            } else {
                {
                    Text(
                        text = nameError,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = spacingExtraLarge,
                    vertical = spacingMedium
                ),
            value = email,
            onValueChange = onEmailChanged,
            isError = !emailError.isNullOrBlank(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text(text = stringResource(id = R.string.hint_enter_email))
            },
            singleLine = true,
            supportingText = if (emailError.isNullOrBlank()) {
                null
            } else {
                {
                    Text(
                        text = emailError,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        val passwordToggleIcon by remember(passwordVisible) {
            mutableStateOf(
                if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
            )
        }
        @StringRes val passwordToggleDescription by remember(passwordVisible) {
            mutableIntStateOf(
                if (passwordVisible) R.string.btn_label_hide_password else R.string.btn_label_show_password
            )
        }
        val passwordVisualTransformation by remember(passwordVisible) {
            mutableStateOf(
                if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = spacingExtraLarge,
                    vertical = spacingMedium
                ),
            value = password,
            onValueChange = onPasswordChanged,
            isError = !passwordError.isNullOrBlank(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (signUpEnabled) {
                        onClickedSignUp()
                    }
                }
            ),
            placeholder = {
                Text(text = stringResource(id = R.string.hint_enter_password))
            },
            visualTransformation = passwordVisualTransformation,
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = passwordToggleIcon,
                        contentDescription = stringResource(id = passwordToggleDescription)
                    )
                }
            },
            supportingText = if (passwordError.isNullOrBlank()) {
                null
            } else {
                {
                    Text(
                        text = passwordError,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = spacingExtraLarge,
                    vertical = spacingMedium
                ),
            onClick = onClickedSignUp,
            enabled = signUpEnabled
        ) {
            Text(text = stringResource(id = R.string.lbl_sign_up))
        }

    }
}

@UiModePreviews
@Composable
private fun SignUpPreview() {
    ThemedPreview {
        SignUpUI(
            modifier = Modifier.fillMaxSize(),
            name = "",
            email = "",
            password = "",
            onNameChanged = {},
            onEmailChanged = {},
            onPasswordChanged = {},
            onClickedSignUp = {}
        )
    }
}