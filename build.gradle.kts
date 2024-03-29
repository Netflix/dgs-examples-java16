/*
 * Copyright 2021 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


plugins {
    id("java")
    id("com.netflix.dgs.codegen") version "5.11.1"
    id("org.springframework.boot") version "2.6.6"
}

apply(plugin = "io.spring.dependency-management")

java.sourceCompatibility = JavaVersion.VERSION_16

group = "com.example"
version = "0.0.1-SNAPSHOT"

// If you use Spring Boot Gradle Plugin 2.3.+ you will have to explicitly set the Kotlin Version to 1.4.+.
// The plugin will downgrade Kotlin to its 1.3.x version, which is not compatible.
// You do this by setting the version into the `extra["kotlin.version"]` e.g:
//
// extra["kotlin.version"] = "1.4.31"

repositories {
    mavenCentral()
    maven {
        url = uri("https://netflixoss.jfrog.io/artifactory/maven-oss-candidates/")
    }
    mavenLocal()
}

dependencies {
    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:5.0.4"))
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
    implementation("com.netflix.graphql.dgs:graphql-dgs-extended-scalars")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.github.javafaker:javafaker:1.+")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
    packageName = "com.example.demo.generated"
    typeMapping = mutableMapOf("DateRange" to "com.example.demo.scalars.DateRange")
}

tasks.withType<JavaCompile> {
    java {
        targetCompatibility = JavaVersion.VERSION_16
        sourceCompatibility = JavaVersion.VERSION_16
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

