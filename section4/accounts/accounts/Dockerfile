#start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#information about the maintainer
MAINTAINER  youssefidbensalah

#copy the jar file into the container
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts.jar

#run the jar file
ENTRYPOINT ["java", "-jar", "accounts.jar"]

