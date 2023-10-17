package com.sevenpeaks.zawmyonaing.readease.analytics

import android.os.Bundle

val Map<String, Any?>.logString: String
    get() {
        val stringBuilder = StringBuilder()
        stringBuilder.append("\n")
        forEach { (k, v) ->
            stringBuilder.append("$k = $v")
            stringBuilder.append("\n")
        }
//        stringBuilder.append("\n")
        return stringBuilder.toString()
    }

val Map<String, Any?>.bundle: Bundle
    get()  {
        val bundle = Bundle()
        forEach { (key, value) ->
            when (value) {
                is String -> bundle.putString(key, value)
                is Int -> bundle.putInt(key, value)
                is Long -> bundle.putLong(key, value)
                is Double -> bundle.putDouble(key, value)
                is Boolean -> bundle.putBoolean(key, value)
                else -> bundle.putString(key, value.toString())
            }
        }
        return bundle
    }