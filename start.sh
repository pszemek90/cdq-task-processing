#!/bin/bash

cd task-processing-model

echo "Running mvn clean install in model module..."
mvn clean install

if [ $? -eq 0 ]; then
    echo "Maven build successful for model module."
else
    echo "Maven build failed."
    exit 1
fi

cd ../task-processing-database

echo "Running mvn clean install in database module..."
mvn clean install

if [ $? -eq 0 ]; then
    echo "Maven build successful for database module."
else
    echo "Maven build failed."
    exit 1
fi

cd ../intake-service

echo "Running mvn clean install in intake-service..."
mvn clean install

if [ $? -eq 0 ]; then
    echo "Running docker build in intake-service..."
    docker build -t taskprocessing-intake-service:0.0.1 .

    if [ $? -eq 0 ]; then
        echo "Build successful!"
    else
        echo "Docker build failed. Please check the logs for errors."
        exit 1
    fi
else
    echo "Maven build failed. Aborting Docker build."
    exit 1
fi

cd ../processor-service

echo "Running mvn clean install in processor-service..."
mvn clean install

if [ $? -eq 0 ]; then
    echo "Running docker build in intake-service..."
    docker build -t taskprocessing-processor-service:0.0.1 .

    if [ $? -eq 0 ]; then
        echo "Build successful!"
    else
        echo "Docker build failed. Please check the logs for errors."
        exit 1
    fi
else
    echo "Maven build failed. Aborting Docker build."
    exit 1
fi