# Utilities
A simple project with reusable tools.

## General Informations
 - Java version : 1.8

## Contents
### Parsing API
#### String parsing
This repository contains a String parsing api, featuring:
 - rawtypes
	• number based rawtypes : min/max support
	• char : expected chars support
 - String : regular expressions support
 - Number : min/max support
 - List : support depends on the element's type
 - Map : support varies on the key/value's type
 - Deep list
	• parse a list, and all maps and lists inside
	• no type support, all non-map and non-list elements are stored as String, and require further parsing
 - Deep map
	• parse a map, and all maps and lists inside
	• no type support, all non-map and non-list elements are stored as String, and require further parsing

The [Parser](/src/main/java/fr/az/util/parsing/string/Parser.java "Parser source code") class possess the simple minimal
definitions of the aforementioned parsers instanciated.

#### JSON parsing
This repository contains a JSON parsing api, based on [org.json](https://mvnrepository.com/artifact/org.json/json "Maven repository for org.json")
It intends to constructs Objects from json in a simple manner, with classes daughters of
[Key](/src/main/java/fr/az/util/parsing/json/keys/Key.java "Key source code") as parsers of a field in a class.
