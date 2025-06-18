plugins {
    alias(libs.plugins.nsh.android.library)
    alias(libs.plugins.nsh.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.nsh.core.network"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    implementation(libs.retrofit.core)
    implementation(libs.converter.scalars)
}
