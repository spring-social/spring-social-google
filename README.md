# Spring Social Google

[![Build Status](https://travis-ci.org/spring-social/spring-social-google.svg?branch=master)](https://travis-ci.org/spring-social/spring-social-google)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg?style=plastic)](https://raw.githubusercontent.com/spring-social/spring-social-google/master/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/spring-social/spring-social-google.svg?style=plastic)](https://github.com/spring-social/spring-social-google/issues)

## Reference Documentation

You can view an early preview of the reference [documentation here](https://spring-social.github.io/spring-social-google/). Lots more to come.

## Goals

Allow a developer to interact, easily, with Google from the Spring ecosystem.

## Installing

### Releases

Production releases are available on Maven central.

In Gradle:

```groovy
compile 'com.github.spring-social:spring-social-google:latest.release'
```

Or in Maven:

```xml
<dependency>
  <groupId>com.github.spring-social</groupId>
  <artifactId>spring-social-google</artifactId>
  <version>${social.version}</version>
</dependency>
```

### Snapshots

Pre-release artifacts are being published frequently, but are NOT intended for production use.

In Gradle:

```groovy
compile 'com.github.spring-social:spring-social-google:latest.snapshot'
```

Or in Maven:

```xml
<dependency>
  <groupId>com.github.spring-social</groupId>
  <artifactId>spring-social-google</artifactId>
  <version>${social.version}</version>
</dependency>
```

## Building Locally

Run `./gradlew pTML` to publish a snapshot to your Maven local repo. To consume:

```groovy
repositories {
    mavenLocal()
}

dependencies {
    compile 'com.github.spring-social:spring-social-google:latest.integration'
}
```

## Available Functionality

* Google+
* Calendar
* Drive
* OAuth2
* Query
* Tasks
