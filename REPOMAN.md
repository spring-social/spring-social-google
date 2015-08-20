Deploy to repo-man. Since this is being deployed to the releases repository you need to BUMP the version number and update the dependees.

I could not figure an easy way to do this from gradlew so I did it manually as such:
```
mvn deploy:deploy-file -Dfile=spring-social-google-1.0.0.BUILD-PERC.jar \
    -DgroupId=org.springframework.social -DartifactId=spring-social-google \
    -Dversion=1.0.0.BUILD-PERC -Dpackaging=jar -DrepositoryId=deployment \
    -Durl=http://repo-man.cloud.percussion.com:8081/nexus/content/repositories/releases/ \
    -s ~/perc-cloud-commons/config/maven-settings.xml
```

TODO: update gradlew to build a pom for publishing to repo-man using the -SNAPSHOT and save release for a real release.
