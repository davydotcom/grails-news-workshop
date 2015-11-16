# Step 3 Instructions

Now that we have figured out how to list news posts as well as submit new ones Lets Implement [Spring Security 3](http://grails-plugins.github.io/grails-spring-security-core/guide/newInV3.html).

To get started we must update our build.gradle file 

The latest grails3 plugins can be found in bintray here: [https://bintray.com/grails/plugins](https://bintray.com/grails/plugins)

```groovy
dependencies {
  compile 'org.grails.plugins:spring-security-core:3.0.0.M1'
  //Temporary Workaround for M2 Release of grails
  compile 'org.springframework.security:spring-security-core:3.2.9.RELEASE'
  compile 'org.springframework.security:spring-security-web:3.2.9.RELEASE'
}
```

Once we have installed the spring security plugin we need to initialize it:

```
grails s2-quickstart grails.news User Role
```

Now that we have initialized spring security we need to setup its interceptUrlMap config in application.yml and be sure to remove the auto generated `application.groovy`. The configuration has already been setup for you in `application.yml` for this lab.

Next we will create a `RegisterController` for registering a user

Finally lets modify our Post record to get associated with a User upon Post creation. 

**NOTE**: You will notice the `PostControllerSpec` and `PostSpec` tests have been adjusted with new use cases due to the User association.

## Functional Tests

In this lab we will also utilize a Geb test to verify that a registration form has been created. Please look at `src/integration-test/groovy/grails/news/RegisterSpec.groovy`.


