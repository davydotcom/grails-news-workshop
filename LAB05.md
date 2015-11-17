# Step 5 Instructions

We have built our grails news application and now its time to prepare for deployment to a real web server.

We can create the necessary packages for deployment by using the `./gradlew assemble` command.

This will create both a fatJar with an embedded tomcat container as well as an embedded war container.

If you want to deploy to a tomcat instance be sure to mark the tomcat start as provided:

```
dependencies {
    provided "org.springframework.boot:spring-boot-starter-tomcat"
}
```

### Bonus

By this time it might be nice to hook up a real datasource to this project so our articles are persisted. Try adding mysql as a valid datasource

**Hint:** [http://stackoverflow.com/questions/31764922/how-to-connect-grails-3-0-to-my-local-mysql-database](http://stackoverflow.com/questions/31764922/how-to-connect-grails-3-0-to-my-local-mysql-database)