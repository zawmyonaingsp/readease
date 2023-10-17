package com.sevenpeaks.zawmyonaing.readease.analytics.events

object AppAnalytics {

    ///////////////////////////////////////////////////////////////////////////
    // Values
    ///////////////////////////////////////////////////////////////////////////
    const val SUBSCRIPTION_PREMIUM = "Premium"
    const val SUBSCRIPTION_FREE = "Free"

    ///////////////////////////////////////////////////////////////////////////
    // User Properties
    ///////////////////////////////////////////////////////////////////////////
    const val USER_EMAIL = "\$email"
    const val USER_NAME = "\$name"
    const val USER_REG_DATE = "Registration Date"
    const val USER_SUBSCRIPTION = "Plan Type"
    const val USER_FIRST_ARTICLE_READ_TIMESTAMP = "First Article Read"
    const val USER_LAST_ARTICLE_READ_TIMESTAMP = "Last Article Read"
    const val USER_LIFETIME_ARTICLE_READ_COUNT = "Lifetime Article Read"
    const val USER_LIFETIME_ARTICLE_RATES_COUNT = "Lifetime Rates Articles Count"

    ///////////////////////////////////////////////////////////////////////////
    // Super Properties
    ///////////////////////////////////////////////////////////////////////////
    const val PLAN_TYPE = "Plan Type"

    ///////////////////////////////////////////////////////////////////////////
    // Onboarding
    ///////////////////////////////////////////////////////////////////////////
    const val EVENT_SCREEN_VIEW_ONBOARDING = "Onboarding Screen View"

    ///////////////////////////////////////////////////////////////////////////
    // Sign UP
    ///////////////////////////////////////////////////////////////////////////
    const val EVENT_SCREEN_VIEW_SIGN_UP = "Sign Up Screen View"
    const val ACTION_SIGN_UP = "Sign Up"

    ///////////////////////////////////////////////////////////////////////////
    // Login
    ///////////////////////////////////////////////////////////////////////////
    const val EVENT_SCREEN_VIEW_LOGIN = "Login Screen View"
    const val ACTION_LOG_IN = "Log in"

    ///////////////////////////////////////////////////////////////////////////
    // Articles List
    ///////////////////////////////////////////////////////////////////////////
    const val EVENT_SCREEN_VIEW_ARTICLE_LIST = "Articles List Screen View"
    const val ACTION_LOG_OUT = "Log out"
    const val ACTION_PREMIUM_SUBSCRIBE = "Premium Subscribe"
    const val ACTION_PREMIUM_UNSUBSCRIBE = "Premium Unsubscribe"

    ///////////////////////////////////////////////////////////////////////////
    // Article Detail
    ///////////////////////////////////////////////////////////////////////////
    const val EVENT_SCREEN_VIEW_ARTICLE_DETAIL = "Article Detail Screen View"
    const val ACTION_RATE_ARTICLE = "Rate Article"

    ///////////////////////////////////////////////////////////////////////////
    // Article Event Params
    ///////////////////////////////////////////////////////////////////////////
    const val PARAM_ARTICLE_ID = "Article ID"
    const val PARAM_ARTICLE_RATING = "Article Rating"
//    const val PARAM_ARTICLE_TITLE = "Article Title"
//    const val PARAM_ARTICLE_AUTHOR = "Author Name"
//    const val PARAM_ARTICLE_CATEGORY = "Article Category"
//    const val PARAM_ARTICLE_PUB_DATE = "Article Published Date"
//    const val PARAM_ARTICLE_PREMIUM = "Is Premium Article"


}