#!/bin/sh

echo "==> Configuring the environment..."

cd /home/app

echo JAVA_OPTS=$JAVA_OPTS

java $JAVA_OPTS -jar demo-0.0.1-SNAPSHOT.jar