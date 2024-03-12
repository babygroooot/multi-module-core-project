import build_logic.configureFlavors
import build_logic.util.configureKotlinAndroid
import build_logic.util.libs
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class FeatureLibraryGradleConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("developmentway.android.hilt")
                apply("androidx.navigation.safeargs.kotlin")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.consumerProguardFiles("consumer-rules.pro")
                configureFlavors(this)
                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "consumer-rules.pro")
                    }
                    debug {
                        proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "consumer-rules.pro")
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