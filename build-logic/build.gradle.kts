import org.gradle.kotlin.dsl.`kotlin-dsl`

repositories {
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.tools.build.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "developmentway.android.library.core"
            implementationClass = "CoreLibraryGradleConventionPlugin"
        }
        register("androidHilt") {
            id = "developmentway.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("androidNetworkLibrary") {
            id = "developmentway.android.library.network"
            implementationClass = "NetworkGradleConventionPlugin"
        }
        register("androidCommonLibrary") {
            id = "developmentway.android.library.common"
            implementationClass = "CommonModuleGradleConventionPlugin"
        }
        register("androidFeatureLibrary") {
            id = "developmentway.android.library.feature"
            implementationClass = "FeatureLibraryGradleConventionPlugin"
        }
        register("androidApplication") {
            id = "developmentway.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }
}