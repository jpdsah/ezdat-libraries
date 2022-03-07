pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Ezdat Libraries"
include(":library:core")
include(":library:core-android-ext")
include(":library:core-android")
include(":library:databinding")
include(":library:view-ktx")
include(":library:core-networking")
