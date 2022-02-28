#STAGE 1
#start with a base image containing Java Runtime
FROM openjdk:11-slim as build

#Add Maintainer Info
LABEL maintainer="Juliherms Vasconcelos <j.a.vasconcelos321@gmail.com>"

#The application's jar file
ARG JAR_FILE

#Add the application's jar to the container
COPY ${JAR_FILE} app.jar

#unpackage jar file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

#STAGE 2
#Same java runtime
FROM openjdk:11-slim

#Add volume pointing to /tmp
VOLUME /tmp

#Copy unpackaged application to new container
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

#execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.github.juliherms.licensing.LicensingServiceApplication"]