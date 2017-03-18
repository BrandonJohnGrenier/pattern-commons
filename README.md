[![Build Status](https://travis-ci.org/PatternFM/commons.svg?branch=master)](https://travis-ci.org/PatternFM/commons)
[![Coverage Status](https://coveralls.io/repos/github/PatternFM/commons/badge.svg?branch=master)](https://coveralls.io/github/PatternFM/commons?branch=master)  

## Introduction

Pattern Commons is a minimal library for common functions including identity generation, JSON parsing and reflection.

To get started, add the following dependency to your depedency list:
```xml
<dependency>
    <groupId>fm.pattern</groupId>
    <artifactId>commons-util</artifactId>
    <version>1.0.7</version>
</dependency>
```

## Identity Generation

The id generator uses a cryptographically strong random number generator to produce sequences of alphanumeric strings that can be used as unique identifiers.

By default, the id generator will produce a 25 character length alphanumeric string, with a mix of uppercase and lowercase characters:  
```
IdGenerator.generateId() => 'jDWatQsPtRNOMhYAnVWUDF3LE"
```

You can specify the length of the id to generate by provding a length argument:
```
IdGenerator.generateId(5) => "pciIi"
IdGenerator.generateId(50) => "Sf3hcRp8yIveqZbkx3AMTpomZzbDVHfxyOIyGzjZCaPaGdc0NC"
```

You can add a prefix to generated ids. The id generator will add an underscore between the specified prefix and the (default 25 character length) alphanumeric sequence.
```
IdGenerator.generateId("txn") => "txn_Lh2LRvtK4euznW686ScmNNkw4"
```

You can specify a prefix and the length of the id to generate:
```
IdGenerator.generateId("txn", 10) => 'txn_qGoxTSqc30'   
IdGenerator.generateId("txn", 30) => 'txn_6CKbkcPQgk8OCONGu82Tv2rv664P2H'
```

## JSON Parsing

The JSON interface exposes a simple API on top of the [Jackson JSON library](https://github.com/FasterXML/jackson) for JSON parsing.

### Java -> JSON
The stringify() method serializes a Java value into a JSON string.
```
Account account = ...;
JSON.stringify(account) => {"id":"acc_ljHmy6Sxfasjz5KsFsXox","created":1489819061925,"updated":1489819061925,"firstName":"John","lastName":"Smith"}
```

If you override your domain model toString() implementation to generate JSON it becomes simple to produce well formatted JSON logs, and really useful when debugging and troubleshooting. 

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

### JSON -> Java
The parse() method deserializes a JSON string into a Java value.
```
Account account = JSON.parse("{\"firstName\":\"John\",\"lastName\":\"Smith\"}", Account.class);

System.out.pritnln(account.getFirstName()); => "John"
System.out.pritnln(account.getLastName()); => "Smith"
```

## Building from Source

Both JDK 8 and Maven 3 are required to build Pattern Commons from source. With these prerequisites in place you can build Pattern Commons by:
```
git clone https://github.com/PatternFM/commons.git
cd commons
mvn clean install
```

## Continuous Integration

The Continuous Integration service for the project is hosted on [Travis](https://travis-ci.org/PatternFM/commons) 


## Licensing

This software is provided and distributed under the Apache Software License 2.0. Refer to LICENSE.txt for more information.