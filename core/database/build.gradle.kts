plugins {
    id("developmentway.android.library.core")
    id("developmentway.android.hilt")

}

android {
    namespace = "com.core.database"
}

dependencies {


    testImplementation (libs.junit.test)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.espresso.core)

    //Room
    implementation (libs.androidx.room)
    ksp (libs.androidx.room.compiler)

    implementation(project(":core:model"))
}