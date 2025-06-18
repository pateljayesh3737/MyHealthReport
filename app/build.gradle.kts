import com.nsh.NshBuildType

plugins {
    alias(libs.plugins.nsh.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.nsh.android.application.compose)
    alias(libs.plugins.nsh.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.nsh.myhealthreport"

    defaultConfig {
        applicationId = "com.nsh.myhealthreport"
        versionCode = 1
        versionName = "1.0"

        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = NshBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1,NOTICE.md}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    // sub modules
    implementation(projects.feature.report)
    implementation(projects.feature.home)

    implementation(projects.core.data)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.adaptive.layout)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.kotlinx.coroutines.guava)
    implementation(libs.coil.kt)
    implementation(libs.androidx.material3.navigation3)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.multidex)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.kotlinx.serialization.json)

    ksp(libs.hilt.compiler)

}