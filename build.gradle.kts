import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    kotlin("jvm")
    application
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta8")
    implementation("com.github.nwillc.ksvg:ksvg:master-SNAPSHOT")

    testImplementation("junit:junit:4.13.2")
    testImplementation(kotlin("test-junit"))

}

tasks.withType<Test> {
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events("passed", "failed", "skipped")
    }
}

application {
    mainClass.set("demos.MainKt")
}