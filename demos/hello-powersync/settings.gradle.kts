enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://jitpack.io") {
            content { includeGroup("com.github.requery") }
        }
    }
    versionCatalogs {
        create("projectLibs") {
            from(files("../../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "hello-powersync"

include(":composeApp")
include(":desktop")

includeBuild("../..") {
    dependencySubstitution {
        substitute(module("com.powersync:core"))
            .using(project(":core")).because("we want to auto-wire up sample dependency")
        substitute(module("com.powersync:connector-supabase"))
            .using(project(":connectors:supabase"))
            .because("we want to auto-wire up sample dependency")
        substitute(module("com.powersync:compose"))
            .using(project(":compose")).because("we want to auto-wire up sample dependency")
    }
}
