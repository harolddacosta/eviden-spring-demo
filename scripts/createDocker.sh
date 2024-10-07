#!/bin/bash

mvn -f ../pom.xml clean package -Dmaven.test.skip=true && cd .. && docker build --no-cache -t eviden-api:latest .
