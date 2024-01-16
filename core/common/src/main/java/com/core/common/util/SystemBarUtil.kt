package com.core.common.util

import android.app.Activity
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat

fun Activity.setStatusBarColor(@ColorRes color: Int){
    window.statusBarColor = ContextCompat.getColor(this, color)
}

fun Activity.setStatusBarAppearance(isLightAppearance: Boolean){
    WindowCompat.getInsetsController(
        window,
        window.decorView
    ).isAppearanceLightStatusBars = isLightAppearance
}