plugins {
    alias(libs.plugins.developmentway.android.library.core)
    alias(libs.plugins.developmentway.android.hilt)
}


android {
    namespace = "com.core.domain"
}

dependencies {

    testImplementation (libs.junit.test)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.espresso.core)
    
    implementation (project(":core:model"))
    implementation (project(":core:data"))

}