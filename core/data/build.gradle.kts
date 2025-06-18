plugins {
    alias(libs.plugins.nsh.android.library)
    alias(libs.plugins.nsh.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.nsh.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.network)
    api(projects.core.model)
}
