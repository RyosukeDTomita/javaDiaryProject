#!/bin/bash
mvn clean
mvn jacoco:prepare-agent test jacoco:report
mvn dependency-check:check
mvn compile site
mvn compile
mvn javadoc:javadoc
mvn package
