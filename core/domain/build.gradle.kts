plugins {
    alias(libs.plugins.nsh.android.library)
    alias(libs.plugins.nsh.hilt)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.nsh.core.domain"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    api(projects.core.data)
    api(projects.core.model)
    implementation(libs.gson)

    implementation(libs.javax.inject)
    api(libs.org.hl7.fhir.r4) {
        exclude(group = "dev.ikm.jpms", module = "guava")
    }
}