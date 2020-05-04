# Utilities
A simple project with reusable tools.

## General Informations
 - Java version : 1.8
 - Latest version : 1.0

### Gradle
If you want to use this api in your project, you should add some code in your `build.gradle` file.
```gradle
repositories {
	//Declare this api's repository
	maven { url = uri('https://maven.pkg.github.com/az-0/Utilities') }
}

dependencies {
	//Declare the api as a dependency for your project
	implementation 'fr.az.util:Utilities:<VERSION>'
}
```

## Contents
### Parsing API
#### String parsing
This repository contains a String parsing api, featuring:
 - Raw Types
	- number based rawtypes : min/max support
	- char : simple char parsing
 - String : regular expressions support.
 - Number : min/max support, parse any number.
 - List : parse a list \[element 1, "element 2", ..., element N], and has support from element's type 
 - Map : parse a map {key 1: value 1, "key 2" = value2, "key N = foo" = "value N: bar"}, and has support from element's type
> Using quotes `"` around an element in a list or a map will ensure the element integrity. Else, the spaces surrounding it may get trimmed, and a special character could cause the parsing to fail. However, using quotes means you have to escape special characters inside the element with an antislash `\`.
 - Deep list
	- parse a list, and all maps and lists inside
	- no type support, all non-map and non-list elements are stored as String, and require further parsing
 - Deep map
	- parse a map, and all maps and lists inside
	- no type support, all non-map and non-list elements are stored as String, and require further parsing

The [Parser](/src/main/java/fr/az/util/parsing/string/Parser.java "Parser source code") interface possess the minimal
definitions of the aforementioned parsers instanciated, as well as raw parsers for raw types.

#### JSON parsing
This repository contains a JSON parsing api, based on [org.json](https://mvnrepository.com/artifact/org.json/json "Maven repository for org.json")
It intends to constructs Objects from json in a simple manner, with classes daughters of
[Key](/src/main/java/fr/az/util/parsing/json/keys/Key.java "Key source code") as parsers of a field in a class. See the documentation of said classes for more informations.
