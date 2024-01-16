plugins {
    id("developmentway.android.library.core")
    id("developmentway.android.hilt")
}


android {
    namespace = "com.core.datastore"
}

dependencies {

    implementation (libs.datastore.preferences)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.converter)

    implementation(project(":core:model"))
}