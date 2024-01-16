
plugins {
    id("developmentway.android.library.network")
    id("developmentway.android.hilt")
}

android {
    namespace = "com.core.network"
}

dependencies {

    testImplementation (libs.junit.test)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.espresso.core)

    //Retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logger)

    implementation (project(":core:model"))
    implementation (project(":core:common"))
    implementation (project(":core:datastore"))
}