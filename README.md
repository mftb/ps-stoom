# Processo Seletivo Stoom

The application is inside **address** directory

Maven was chosen as dependency manager

There is a Dockerfile provided in address/Dockerfile to run the application with docker

## How to run the application with docker
```
$ cd address
$ docker run -p 8080:8080 -t springio/gs-spring-boot-docker
```

## How to run the application without docker
```
$ cd address
$ ./mvnw clean spring-boot:run
```

## How to run the tests
```
$ cd address
$ ./mvnw test
```