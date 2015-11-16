# Step 1 Instructions

The objective is to create an auth restricted hacker-news like web app. It should provide a list of articles with support for ranking and commenting.

To get started we are going to create a Domain Class for storing News Posts called `Post`.

```bash
grails create-domain-class Post
```

The objective is to get all tests within the lab to pass. The Tests in question can be found in the `test/groovy/grails/news/PostSpec.groovy` file.

To Run tests run `grails test-app`
