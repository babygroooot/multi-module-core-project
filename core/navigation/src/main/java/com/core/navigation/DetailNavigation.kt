package com.core.navigation

import android.content.Intent

object DetailNavigation: Navigation {
    private const val DETAIL = "com.example.detail.DetailActivity"
    override fun getIntent(): Intent? = DETAIL.loadIntentOrNull()
}