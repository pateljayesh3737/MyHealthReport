plugins {
    alias(libs.plugins.nsh.android.feature)
    alias(libs.plugins.nsh.android.library.compose)
    alias(libs.plugins.nsh.hilt)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.nsh.feature.report"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material3.adaptive)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.util)

    implementation(libs.javax.inject)
}