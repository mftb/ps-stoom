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

## How to interact with the application
### Retrieve all addresses
```
GET http://localhost:8080/Address
```

### Retrieve an address
```
GET http://localhost:8080/Address/<id>
```

### Delete an address
```
DELETE http://localhost:8080/Address/<id>
```

### Create a new address
```
POST http://localhost:8080/Address
```

Request body:
```JSON
{
  "streetName":"streetName",
  "number": 123,
  "complement":"complement",
  "neighbourhood":"neighbourhood",
  "city":"city",
  "state":"state",
  "country":"country",
  "zipcode":"zipcode",
  "latitude":"latitude",
  "longitude":"longitude"
}
```

### Update an existing address
```
PUT http://localhost:8080/Address/<id>
```

Request body:
```JSON
{
  "streetName":"streetName",
  "number": 123,
  "complement":"complement",
  "neighbourhood":"neighbourhood",
  "city":"city",
  "state":"state",
  "country":"country",
  "zipcode":"zipcode",
  "latitude":"latitude",
  "longitude":"longitude"
}
```
