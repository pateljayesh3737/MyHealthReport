pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://androidx.dev/snapshots/builds/13617490/artifacts/repository")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://androidx.dev/snapshots/builds/13617490/artifacts/repository")
        }
    }
}

rootProject.name = "MyHealthReport"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:data")
include(":core:network")
include(":core:model")
include(":core:domain")
include(":feature:home")
include(":feature:report")
include(":core:common")

check(JavaVersion.current().isCompatibleWith(JavaVersion.VERSION_17)) {
    """
    This project requires JDK 17+ but it is currently using JDK ${JavaVersion.current()}.
    Java Home: [${System.getProperty("java.home")}]
    https://developer.android.com/build/jdks#jdk-config-in-studio
    """.trimIndent()
}