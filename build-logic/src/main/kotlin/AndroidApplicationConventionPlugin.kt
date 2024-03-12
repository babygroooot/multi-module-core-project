import build_logic.AppFlavor
import build_logic.DefaultConfig
import build_logic.FlavorConfig
import build_logic.configureFlavors
import build_logic.util.configureKotlinAndroid
import build_logic.util.libs
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("developmentway.android.hilt")
                apply("androidx.navigation.safeargs")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig {
                    applicationId = DefaultConfig.APPLICATION_ID
                    targetSdk = DefaultConfig.TARGET_SDK
                    minSdk = DefaultConfig.MIN_SDK
                    versionCode = DefaultConfig.VERSION_CODE
                    versionName = DefaultConfig.VERSION_NAME
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                buildTypes {
                    release {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        isDebuggable = false
                        proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                    debug {
                        isMinifyEnabled = false
                        isShrinkResources = false
                        proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }
                configureFlavors(this) {
                    when(it) {
                        AppFlavor.Dev -> {
                            manifestPlaceholders["appName"] = FlavorConfig.DEV_APP_NAME
                            manifestPlaceholders["appIcon"] = FlavorConfig.ICON_LAUNCHER_DEV
                            manifestPlaceholders["appIconRound"] = FlavorConfig.ICON_LAUNCHER_ROUND_DEV
                        }
                        AppFlavor.Prod -> {
                            manifestPlaceholders["appName"] = FlavorConfig.PROD_APP_NAME
                            manifestPlaceholders["appIcon"] = FlavorConfig.ICON_LAUNCHER_PROD
                            manifestPlaceholders["appIconRound"] = FlavorConfig.ICON_LAUNCHER_ROUND_PROD
                        }
                    }
                }
                buildFeatures {
                    viewBinding = true
                    buildConfig = true
                }
                dependencies {
                    add("implementation", project(":core:model"))
                    add("implementation", project(":core:domain"))
                    add("implementation", project(":core:common"))
                    add("testImplementation", libs.findLibrary("junit.test").get())
                    add("androidTestImplementation", libs.findLibrary("androidx.test.ext").get())
                    add("androidTestImplementation", libs.findLibrary("espresso.core").get())
                    add("implementation", libs.findLibrary("material.ui").get())
                    add("implementation", libs.findLibrary("androidx.core.ktx").get())
                    add("implementation", libs.findLibrary("androidx.appcompat").get())
                    add("implementation", libs.findLibrary("androidx.fragment").get())
                    add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel").get())
                    add("implementation", libs.findLibrary("androidx.lifecycle.runtime").get())
                    add("implementation", libs.findLibrary("androidx.navigation.fragment").get())
                    add("implementation", libs.findLibrary("androidx.navigation.ui").get())
                    add("implementation", libs.findLibrary("constraint.layout").get())
                }
            }
        }
    }

}