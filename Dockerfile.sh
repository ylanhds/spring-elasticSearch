#! /bin/bash
cd $(dirname "${BASH_SOURCE[0]}") && pwd
mvn clean
mvn package
docker build -t spring-elasticsearch .
docker run -d --name spring-elasticsearch -p 8080:8080 spring-elasticsearch

