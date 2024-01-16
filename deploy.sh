#!/bin/bash

lein uberjar
version="0.0.8"

mv target/firma-analysis-$version-standalone.jar target/firma-analysis.jar

sed -i "" "s/<version>.*<\/version>/<version>$version<\/version>/" pom.xml

lein with-profile uberjar deploy clojars org.clojars.mtsbarbosa/firma-analysis $version target/firma-analysis.jar pom.xml
