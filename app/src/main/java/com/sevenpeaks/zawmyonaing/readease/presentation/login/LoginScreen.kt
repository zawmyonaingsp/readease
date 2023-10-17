package com.sevenpeaks.zawmyonaing.readease.presentation.login

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
import com.sevenpeaks.zawmyonaing.readease.analytics.compose.TrackScreenViewEvent
import com.sevenpeaks.zawmyonaing.readease.analytics.events.AppAnalytics
import com.sevenpeaks.zawmyonaing.readease.presentation.common.ScreenHeroText
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingExtraLarge
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingMedium
import com.sevenpeaks.zawmyonaing.readease.ui.widgets.ThemedPreview
import com.sevenpeaks.zawmyonaing.readease.utils.android.compose.preview.UiModePreviews

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginScreenState: LoginScreenState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onClickedLogin: () -> Unit,
) {
    TrackScreenViewEvent(name = AppAnalytics.EVENT_SCREEN_VIEW_LOGIN)

    LoginUI(
        modifier = modifier.fillMaxSize(),
        email = loginScreenState.email,
        emailError = loginScreenState.emailError?.asString(),
        password = loginScreenState.password,
        passwordError = loginScreenState.passwordError?.asString(),
        onEmailChanged = onEmailChanged,
        onPasswordChanged = onPasswordChanged,
        onClickedLogin = onClickedLogin,
        loginEnabled = loginScreenState.loginEnabled
    )
}

@Composable
private fun LoginUI(
    modifier: Modifier,
    email: String,
    emailError: String? = null,
    password: String,
    passwordError: String? = null,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onClickedLogin: () -> Unit,
    loginEnabled: Boolean = false
) {
    Column(
        modifier = modifier
    ) {

        ScreenHeroText(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            message = stringResource(id = R.string.lbl_login_hero_title)
        )

        Spacer(modifier = Modifier.height(spacingExtraLarge))

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
            keyboardActions = KeyboardActions(onDone = { onClickedLogin() }),
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
            enabled = loginEnabled,
            onClick = onClickedLogin
        ) {
            Text(text = stringResource(id = R.string.lbl_login))
        }

    }
}

@UiModePreviews
@Composable
private fun LoginUIPreview() {
    ThemedPreview {
        LoginUI(
            modifier = Modifier.fillMaxSize(),
            email = "",
            password = "",
            onEmailChanged = {},
            onPasswordChanged = {},
            onClickedLogin = {}
        )
    }
}