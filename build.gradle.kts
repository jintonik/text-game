plugins {
    kotlin("jvm") version "2.1.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.googlecode.lanterna:lanterna:3.1.3")
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("org.example.MainKt")
}

tasks.shadowJar {
    archiveBaseName.set("game") // Customize the output JAR name
    archiveClassifier.set("") // Remove the "-all" classifier if desired
    manifest {
        attributes(mapOf("Main-Class" to application.mainClass.get())) // Specify your main class
    }
}
//tasks.jar {
//    manifest {
//        attributes["Main-Class"] = application.mainClass.get()
//    }
//}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}