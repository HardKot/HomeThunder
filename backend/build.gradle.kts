plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
//    id("dev.hilla") version "2.5.5"
}

group = "com.homethunder"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["hillaVersion"] = "2.5.5"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mail")

    implementation("com.leakyabstractions:result:0.15.0.1")

//    implementation("dev.hilla:hilla-react-spring-boot-starter")
    implementation("org.flywaydb:flyway-core")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")

    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    implementation("io.minio:minio:8.4.3")

    implementation ("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.12.5")
}

dependencyManagement {
    imports {
//        mavenBom("dev.hilla:hilla-bom:${property("hillaVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

//
//hilla {
//
//}
