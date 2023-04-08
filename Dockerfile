FROM openjdk:17
EXPOSE 8080
COPY target/G1-Authentication-service-pms-0.0.1-SNAPSHOT.jar G1-Authentication-service-pms-0.0.1-SNAPSHOT
CMD [ "java","-jar","G1-Authentication-service-pms-0.0.1-SNAPSHOT.jar" ]