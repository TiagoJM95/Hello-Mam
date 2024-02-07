FROM maven:3.9.6-amazoncorretto-21-debian AS build
WORKDIR /home/app
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM registry.access.redhat.com/ubi8/openjdk-21:1.18

COPY --from=build --chown=185 /home/app/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build --chown=185 /home/app/target/quarkus-app/*.jar /deployments/
COPY --from=build --chown=185 /home/app/target/quarkus-app/app/ /deployments/app/
COPY --from=build --chown=185 /home/app/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185
ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]