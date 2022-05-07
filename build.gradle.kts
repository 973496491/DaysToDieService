import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

apply(plugin = "io.spring.dependency-management")

plugins {
    kotlin("jvm") version "1.6.20"
    id("org.springframework.boot") version "2.6.7"
    java
}

group = "com.loko.utils"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo1.maven.org/maven2/")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

tasks.getByName<BootJar>("bootJar") {
    launchScript()
}

dependencies {
    implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
    implementation("com.sun.xml.bind:jaxb-impl:3.0.2")
    implementation("com.sun.xml.bind:jaxb-core:3.0.2")
    implementation("javax.activation:activation:1.1.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.0")
    implementation("mysql:mysql-connector-java:8.0.29")
    implementation("org.springframework.boot:spring-boot-maven-plugin:2.6.7")
    implementation("org.springframework.boot:spring-boot-starter:2.6.7")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.7")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.2.2")
    implementation("com.qcloud:cos_api:5.6.77")
    // redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis:2.6.7")
    implementation("org.apache.commons:commons-pool2:2.11.1")
}

java.sourceCompatibility = JavaVersion.VERSION_1_8

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}