[![Build Status](https://travis-ci.org/PatternFM/commons.svg?branch=master)](https://travis-ci.org/PatternFM/commons)
[![Coverage Status](https://coveralls.io/repos/github/PatternFM/commons/badge.svg?branch=master)](https://coveralls.io/github/PatternFM/commons?branch=master)  

# Introduction

Pattern Commons is a minimal library for common functions including identity generation, JSON parsing and reflection.

To get started, add the following dependency to your depedency list:
```xml
<dependency>
    <groupId>fm.pattern</groupId>
    <artifactId>commons-util</artifactId>
    <version>1.0.8</version>
</dependency>
```

## Identity Generation

The id generator uses a cryptographically strong random number generator to produce sequences of alphanumeric strings that can be used as unique identifiers.

By default, the id generator will produce a 25 character length alphanumeric string, with a mix of uppercase and lowercase characters:  
```
IdGenerator.generateId() => "jDWatQsPtRNOMhYAnVWUDF3LE"
```

You can specify the length of the id to generate by provding a length argument:
```java
IdGenerator.generateId(5) => "pciIi"
IdGenerator.generateId(50) => "Sf3hcRp8yIveqZbkx3AMTpomZzbDVHfxyOIyGzjZCaPaGdc0NC"
```

You can add a prefix to generated ids. The id generator will add an underscore between the specified prefix and the (default 25 character length) alphanumeric sequence.
```java
IdGenerator.generateId("txn") => "txn_Lh2LRvtK4euznW686ScmNNkw4"
```

You can specify a prefix and the length of the id to generate:
```java
IdGenerator.generateId("txn", 10) => "txn_qGoxTSqc30" 
IdGenerator.generateId("txn", 30) => "txn_6CKbkcPQgk8OCONGu82Tv2rv664P2H"
```

## JSON Parsing

The JSON API exposes a minimal, JavaScript-inspired interface on top of the excellent [Jackson library](https://github.com/FasterXML/jackson) for JSON parsing.

#### Converting Java to JSON
The stringify() method serializes a Java value into a JSON string.
```java
Person person = new Person("John", "Smith");
JSON.stringify(person) => {"id":"ljHmy6s3Alr7Sxfasjz5KsF","firstName":"John","lastName":"Smith"}
```
This method will throw a *JsonParsingException* at runtime if the Java value cannot be serialized.

***Tip:*** If you override the toString() implementation to generate JSON it becomes simple to produce well formatted JSON logs, and really useful when debugging and troubleshooting. 

```java
public class Account {
    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}

Account account = new Account();

// Logs a well formatted JSON string with the account's current state.
logger.info(account);

// Prints a well formatted JSON string with the account's current state instead of it's hash code.
System.out.pritnln(account);

```

#### Converting JSON to Java
The parse() method deserializes a JSON string into a Java value.
```java
Person person = JSON.parse("{\"firstName\":\"John\",\"lastName\":\"Smith\"}", Person.class);

System.out.pritnln(person.getFirstName()); => "John"
System.out.pritnln(person.getLastName()); => "Smith"
```
This method will throw a *JsonParsingException* at runtime if the JSON string cannot be deserialized.

## Reflection
The Reflection API provides a simple interface on top of the Java Reflection API for getting and setting property values on Java objects.

#### Getting Values

```java
public class Entity {
    
    private String id;
    ...
    
}

public class Address extends Entity {
    
    private String suburb;
    ...
    
}

public class Person extends Entity {
    
    private String name;
    private Address address;
    ...
    
}


Address address = new Address();
address.setId("1234abcd");
address.setSuburb("Bondi");

Person person = new Person();
person.setId("abcd12345");
person.setName("Sally");
person.setAddress(address);


// Retrieves the 'id' property from the Person Entity superclass.
Reflection.get(person, "id"); => "abcd12345"

// Retrieves the 'name' property from the Person object.
Reflection.get(person, "name"); => "Sally"

// Retrieves the 'suburb' property from the composed Address object.
Reflection.get(person, "address.suburb"); => "Bondi"

// Retrieves the 'id' property from the composed Address Entity superclass.
Reflection.get(person, "address.id"); => "1234abcd"

// Retrieves the 'address' property from the Person object, returned as an Address through an explicit cast.
Address address = (Address) Reflection.get(person, "address");

// Retrieves the 'address' property from the Person object, returned as an Address by specifying a return type.
Address address = Reflection.get(person, "address", Address.class);
```

#### Getting Values



# Building from Source

Both JDK 8 and Maven 3 are required to build Pattern Commons from source. With these prerequisites in place you can build Pattern Commons by:
```
git clone https://github.com/PatternFM/commons.git
cd commons
mvn clean install
```

# Continuous Integration

The Continuous Integration service for the project is hosted on [Travis](https://travis-ci.org/PatternFM/commons) 


# Licensing

This software is provided and distributed under the Apache Software License 2.0. Refer to LICENSE.txt for more information.