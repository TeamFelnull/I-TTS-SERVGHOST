import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

base {
    archivesName.set("itts-servghost")
}

tasks.named<Jar>("jar") {
    manifest {
        attributes("Main-Class" to "dev.felnull.itts.Main")
        attributes("Implementation-Version" to project.version)
    }
}

plugins {
    id("java")
    id("java-library")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

repositories {
    mavenCentral()
}
val shadowIn by configurations.creating
configurations {
    shadowIn
    implementation.get().extendsFrom(shadowIn)
}


dependencies {
    val coreVersion = project.extra["core_version"]

    shadowIn("dev.felnull:itts-core:$coreVersion")
    shadowIn(project(":share", "default"))

    shadowIn("mysql:mysql-connector-java:8.0.32")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.named<ShadowJar>("shadowJar") {
    shadowIn.isTransitive = true
    configurations = listOf(shadowIn)
    archiveClassifier.set("")
    dependencies {
        // include(dependency(":core"))
    }
}

tasks.named("build") {
    dependsOn(tasks.named("shadowJar"))
}