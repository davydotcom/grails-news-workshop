# Grails News Example

This is a test driven tutorial for building a grails app.
In this tutorial you will build an app similar to hackerNews. You will start with failed tests as you iterate through each stage of the lab and attempt to get the tests to pass.

The lab covers the following grails concepts:

* Domains
* Controllers
* Views
* Functional and Unit Tests
* Security
* Services and Plugins

## Prerequisites

* Java 8 (7 will work as well)
* Git
* [Sdk Manager](http://sdkman.io/) or grails 3.1.0.M2 

## Setup

1. Install grails 3.1.0.M2 with `sdk install grails 3.1.0.M2`
2. Clone the repository down to your machine:
```
git clone https://github.com/davydotcom/grails-news.git
cd grails-news
```


## Instructions

This tutorial is comprised of numbered tutorials kept in branches in the repository. As you get started simply checkout the lab by name i.e. 

```
git checkout lab01 
```

You can see the answer by going to 

```
git checkout solution01
```

## Steps

* `lab01` - Creating a Post class
* `lab02` - Creating a Controller for listing and creating Posts
* `lab03` - Implementing Spring Security
* `lab04` - Adding Scoring via Services
