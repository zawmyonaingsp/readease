package com.sevenpeaks.zawmyonaing.readease.analytics

import android.annotation.SuppressLint
import android.content.Context
import com.mixpanel.android.mpmetrics.MixpanelAPI
import com.sevenpeaks.zawmyonaing.readease.BuildConfig
import com.sevenpeaks.zawmyonaing.readease.analytics.events.AppAnalytics
import com.sevenpeaks.zawmyonaing.readease.utils.kotlin.DateTimeFormat
import com.sevenpeaks.zawmyonaing.readease.utils.kotlin.currentUTCTime
import com.sevenpeaks.zawmyonaing.readease.utils.kotlin.format
import timber.log.Timber

object AnalyticsManager {

    @SuppressLint("StaticFieldLeak")
    private lateinit var mixpanelAPI: MixpanelAPI

    fun init(context: Context, trackAutomaticEvents: Boolean = true) {
        Timber.d("init() called")

        if (this::mixpanelAPI.isInitialized) {
            Timber.d("init: Duplicate initialize. No-Op!")
            return
        }

        val token = BuildConfig.TOKENMIXPANEL
        mixpanelAPI =
            MixpanelAPI.getInstance(context.applicationContext, token, trackAutomaticEvents)
    }

    fun signIn(
        email: String,
        userName: String?,
        subscriptionPlan: String = AppAnalytics.SUBSCRIPTION_FREE
    ) {
        Timber.d("signIn() called with: email = [$email], userName = [$userName]")
        mixpanelAPI.identify(email, true)
        mixpanelAPI.registerSuperPropertiesMap(
            mapOf(
                AppAnalytics.PLAN_TYPE to subscriptionPlan
            )
        )
        with(mixpanelAPI.people) {
            set(AppAnalytics.USER_NAME, userName)
            set(AppAnalytics.USER_EMAIL, email)
            set(AppAnalytics.USER_SUBSCRIPTION, subscriptionPlan)
            setOnce(
                AppAnalytics.USER_REG_DATE,
                currentUTCTime.format(DateTimeFormat.server_date_time)
            )
        }
    }

    fun signOut() {
        Timber.d("signOut() called")
        logEvent(AppAnalytics.ACTION_LOG_OUT)
        mixpanelAPI.reset()
    }

    fun premiumSubscribe() {
        logEvent(AppAnalytics.ACTION_PREMIUM_SUBSCRIBE)
        setUserProperty(
            AppAnalytics.USER_SUBSCRIPTION,
            AppAnalytics.SUBSCRIPTION_PREMIUM
        )
        mixpanelAPI.registerSuperPropertiesMap(
            mapOf(
                AppAnalytics.PLAN_TYPE to AppAnalytics.SUBSCRIPTION_PREMIUM
            )
        )
    }

    fun premiumUnsubscribe() {
        logEvent(AppAnalytics.ACTION_PREMIUM_UNSUBSCRIBE)
        setUserProperty(
            AppAnalytics.USER_SUBSCRIPTION,
            AppAnalytics.SUBSCRIPTION_FREE
        )
        mixpanelAPI.registerSuperPropertiesMap(
            mapOf(
                AppAnalytics.PLAN_TYPE to AppAnalytics.SUBSCRIPTION_FREE
            )
        )
    }

    fun logEvent(
        eventName: String,
        params: Map<String, Any?> = emptyMap()
    ) {
        Timber.d("logEvent() called with: eventName = [$eventName], params = [${params.logString}]")
        if (params.isEmpty()) {
            mixpanelAPI.track(eventName)
        } else {
            mixpanelAPI.trackMap(eventName, params)
        }
    }

    fun logTimingEvent(
        eventName: String,
        params: Map<String, Any?> = emptyMap()
    ): () -> Unit {
        Timber.d("logTimingEvent() called with: eventName = [$eventName], params = [${params.logString}]")
        val stopTrigger = {
            Timber.d("logTimingEvent() Timer stopped")
            mixpanelAPI.trackMap(eventName, params)
        }
        mixpanelAPI.timeEvent(eventName)
        return stopTrigger
    }

    fun setUserProperty(name: String, value: Any?) {
        Timber.d("setUserProperty() called with: name = [$name], value = [$value]")
        mixpanelAPI.people.set(name, value)
    }

    fun setUserPropertyOnce(name: String, value: Any?) {
        Timber.d("setUserPropertyOnce() called with: name = [$name], value = [$value]")
        mixpanelAPI.people.setOnce(name, value)
    }

    fun incrementUserProperty(name: String, value: Double) {
        Timber.d("incrementUserProperty() called with: name = [$name], value = [$value]")
        mixpanelAPI.people.increment(name, value)
    }

}