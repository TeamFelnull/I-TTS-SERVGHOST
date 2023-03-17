allprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.felnull.dev") }
    }
}

subprojects {
    apply(plugin = "java")

    group = rootProject.group
    version = rootProject.version
}