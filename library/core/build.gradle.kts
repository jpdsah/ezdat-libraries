plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.ezdat.core"
            artifactId = "core"
            version = "1.0.0"

            from(components["java"])
        }
    }
}