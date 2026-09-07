plugins {
    java
    war
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.sunmoon.kds"
version = "0.1.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

// KDS (Kitchen Display System) — sun-moon-java-platform family.
// Redis-backed: tickets are a small, hot, frequently-updated working set
// (active kitchen queue), not the kind of data that needs a durable
// document store. See docs/adr/0001 in the parent sun-moon-java-platform
// repo for the reasoning behind Redis-for-KDS / Mongo-for-Order-Delivery.
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    implementation("io.micrometer:micrometer-registry-prometheus")

    // See sun-moon-java-platform-order's build.gradle.kts / ADR 0005 for
    // why this exact version is pinned and why slf4j-api needs the
    // providedRuntime exclude below — same WAR/Jetty deployment, same bug.
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    implementation("org.slf4j:slf4j-api:2.0.16")

    providedRuntime("org.springframework.boot:spring-boot-starter-jetty") {
        exclude(group = "org.slf4j", module = "slf4j-api")
    }

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
