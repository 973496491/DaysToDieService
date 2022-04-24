import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
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

dependencies {
    implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
    implementation("com.sun.xml.bind:jaxb-impl:3.0.1")
    implementation("com.sun.xml.bind:jaxb-core:3.0.1")
    implementation("javax.activation:activation:1.1.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.0")
    implementation("mysql:mysql-connector-java:8.0.25")
    implementation("org.springframework.boot:spring-boot-maven-plugin:2.6.1")
    implementation("org.springframework.boot:spring-boot-starter:2.6.1")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.1")
    implementation("com.google.code.gson:gson:2.8.2")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
    implementation("com.qcloud:cos_api:5.6.54")
}

group = "me.97349"
version = "1.0-SNAPSHOT"
description = "consoleApp"
java.sourceCompatibility = JavaVersion.VERSION_1_8

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
