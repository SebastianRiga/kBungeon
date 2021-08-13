import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val mainClass: String by project

val kotlinxCoroutinesCoreVersion: String by project
val moshiVersion: String by project
val kotlinLoggingVersion: String by project
val zirconCoreVersion: String by project
val zirconSwingVersion: String by project
val mockitoCoreVersion: String by project
val assertjCoreVersion: String by project
val junitApiVersion: String by project
val junitEngineVersion: String by project

plugins {
	kotlin("jvm") version "1.5.20" // Kotlin base
	id("com.github.johnrengelman.shadow") version "7.0.0" // Fat jar support
}

repositories {
	mavenCentral()
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
	named<ShadowJar>("shadowJar") {
		archiveClassifier.set("")
		archiveVersion.set("")
		mergeServiceFiles()
		manifest {
			attributes(mapOf("Main-Class" to mainClass))
		}
	}
	build {
		dependsOn(shadowJar)
	}
	test {
		useJUnitPlatform()
	}
}

tasks.getting(Jar::class) {
	manifest {
		attributes["Main-Class"] = mainClass
	}
}

dependencies {
	// Kotlin core
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesCoreVersion")

	// JSON parsing
	implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")

	// Logging
	implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingVersion")

	// Zircon - Tile Engine & Text GUI
	implementation("org.hexworks.zircon:zircon.core-jvm:$zirconCoreVersion")
	implementation("org.hexworks.zircon:zircon.jvm.swing:$zirconSwingVersion")

	// Test dependencies
	testImplementation("org.mockito:mockito-core:$mockitoCoreVersion")
	testImplementation("org.assertj:assertj-core:$assertjCoreVersion")
	testImplementation("org.junit.jupiter:junit-jupiter-api:$junitApiVersion")

	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitEngineVersion")
}

