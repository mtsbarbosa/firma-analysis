FROM openjdk:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/firma-analysis-0.0.1-SNAPSHOT-standalone.jar /firma-analysis/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/firma-analysis/app.jar"]
