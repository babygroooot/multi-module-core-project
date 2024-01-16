plugins {
    id("developmentway.android.library.core")
    id("developmentway.android.hilt")
}


android {
    namespace = "com.core.data"
}

dependencies {

    //Network
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logger)

    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":core:network"))
    implementation (project(":core:common"))
}