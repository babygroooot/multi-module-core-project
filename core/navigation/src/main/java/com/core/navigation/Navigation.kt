package com.core.navigation

import android.content.Intent

interface Navigation {
    fun getIntent(): Intent?
}