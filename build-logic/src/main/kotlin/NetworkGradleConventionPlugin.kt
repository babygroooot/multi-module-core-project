import build_logic.AppFlavor
import build_logic.FlavorConfig
import build_logic.configureFlavors
import build_logic.util.configureKotlinAndroid
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class NetworkGradleConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.consumerProguardFiles("consumer-rules.pro")
                configureFlavors(this) {
                    when (it) {
                        AppFlavor.Dev -> buildConfigField("String","BASE_URL", FlavorConfig.DEV_BASE_URL)
                        AppFlavor.Prod -> buildConfigField("String","BASE_URL", FlavorConfig.PROD_BASE_URL)
                    }
                }
                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "consumer-rules.pro")
                    }
                    debug {
                        proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "consumer-rules.pro")
                    }
                }
            }
        }
    }

}