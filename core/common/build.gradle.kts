plugins {
    alias(libs.plugins.nsh.jvm.library)
    alias(libs.plugins.nsh.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
//    testImplementation(libs.kotlinx.coroutines.test)
//    testImplementation(libs.turbine)
}