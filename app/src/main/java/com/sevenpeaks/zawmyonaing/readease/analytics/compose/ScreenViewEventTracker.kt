package com.sevenpeaks.zawmyonaing.readease.analytics.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import com.sevenpeaks.zawmyonaing.readease.analytics.AnalyticsManager
import timber.log.Timber

@Composable
fun TrackScreenViewEvent(
    name: String,
    params: Map<String, Any?> = emptyMap()
) {
    LaunchedEffect(key1 = Unit) {
        Timber.d("TrackScreenView($name) called")
        AnalyticsManager.logEvent(eventName = name, params = params)
    }
}

@Composable
fun TrackTimingScreenViewEvent(
    name: String,
    params: Map<String, Any?> = emptyMap(),
    additionalAction: (() -> Unit)? = null
) {
    DisposableEffect(
        key1 = Unit,
        effect = {
            Timber.d("TrackTimingScreenViewEvent: Started")
            val stopTrigger = AnalyticsManager.logTimingEvent(name, params)
            onDispose {
                Timber.d("TrackTimingScreenViewEvent: Stopped")
                stopTrigger()
                additionalAction?.invoke()
            }
        }
    )
}