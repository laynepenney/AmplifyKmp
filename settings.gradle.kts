enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven { url = File(rootDir, "repo").toURI() }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = File(rootDir, "repo").toURI() }
    }
}

rootProject.name = "amplify-kmp"
include(":amplify")