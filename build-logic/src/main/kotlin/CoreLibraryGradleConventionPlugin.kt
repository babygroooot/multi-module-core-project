import build_logic.configureFlavors
import build_logic.util.configureKotlinAndroid
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class CoreLibraryGradleConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
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
                    buildConfig = true
                }
            }
        }
    }
}