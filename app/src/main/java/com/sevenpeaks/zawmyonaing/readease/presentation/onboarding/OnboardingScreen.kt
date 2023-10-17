package com.sevenpeaks.zawmyonaing.readease.presentation.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sevenpeaks.zawmyonaing.readease.R
import com.sevenpeaks.zawmyonaing.readease.analytics.compose.TrackScreenViewEvent
import com.sevenpeaks.zawmyonaing.readease.analytics.events.AppAnalytics
import com.sevenpeaks.zawmyonaing.readease.presentation.common.ScreenHeroText
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingExtraLarge
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingMedium
import com.sevenpeaks.zawmyonaing.readease.ui.widgets.ThemedPreview
import com.sevenpeaks.zawmyonaing.readease.utils.android.compose.preview.UiModePreviews

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onClickedSignUp: () -> Unit,
    onClickedLogin: () -> Unit,
) {

    TrackScreenViewEvent(name = AppAnalytics.EVENT_SCREEN_VIEW_ONBOARDING)

    OnboardingUI(
        modifier = modifier.fillMaxSize(),
        onClickedSignUp = onClickedSignUp,
        onClickedLogin = onClickedLogin
    )
}

@Composable
private fun OnboardingUI(
    modifier: Modifier = Modifier,
    onClickedSignUp: () -> Unit,
    onClickedLogin: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {

        ScreenHeroText(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            message = stringResource(id = R.string.lbl_welcome_onboarding)
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.3f))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = spacingExtraLarge,
                    vertical = spacingMedium
                ),
            onClick = onClickedLogin
        ) {
            Text(text = stringResource(id = R.string.lbl_login))
        }

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = spacingExtraLarge,
                    vertical = spacingMedium
                ),
            onClick = onClickedSignUp
        ) {
            Text(text = stringResource(id = R.string.lbl_sign_up))
        }

    }
}

@UiModePreviews
@Composable
private fun OnBoardingUIPreview() {
    ThemedPreview {
        OnboardingUI(
            modifier = Modifier.fillMaxSize(),
            onClickedSignUp = {},
            onClickedLogin = {}
        )
    }
}