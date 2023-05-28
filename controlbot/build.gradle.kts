import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

base {
    archivesName.set("itts-servghost-controlbot")
}

tasks.named<Jar>("jar") {
    manifest {
        attributes("Main-Class" to "dev.felnull.itts.servghost.control.Main")
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
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    shadowIn("org.apache.logging.log4j:log4j-core:2.18.0")
    shadowIn("com.google.code.gson:gson:2.10")

    shadowIn(project(":share", "default"))
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