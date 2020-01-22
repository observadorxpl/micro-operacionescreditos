FROM openjdk:8
VOLUME /tmp
ADD ./target/OperacionesCreditoMS-0.0.1-SNAPSHOT.jar micro-operacionescredito.jar
ENTRYPOINT ["java","-jar","/micro-operacionescredito.jar"]