import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.nsh.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.stdlib)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = libs.plugins.nsh.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidApplication") {
            id = libs.plugins.nsh.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = libs.plugins.nsh.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = libs.plugins.nsh.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidFeature") {
            id = libs.plugins.nsh.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("hilt") {
            id = libs.plugins.nsh.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }

        register("androidLint") {
            id = libs.plugins.nsh.android.lint.get().pluginId
            implementationClass = "AndroidLintConventionPlugin"
        }

        register("jvmLibrary") {
            id = libs.plugins.nsh.jvm.library.get().pluginId
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}