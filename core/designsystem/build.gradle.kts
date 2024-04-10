plugins {
    alias(libs.plugins.developmentway.android.library.core)
    alias(libs.plugins.developmentway.android.library.compose)
}

android {
    namespace = "com.core.designsystem"
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.util)
}