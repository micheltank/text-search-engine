FROM maven:3.6.3-jdk-11-slim AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package

FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=build /project/target/text-search-engine-*.jar /app/application.jar
WORKDIR /app
RUN mkdir /files
RUN chown -R javauser:javauser /app
USER javauser

ENTRYPOINT [ "java", "-jar", "application.jar" ]
CMD [ "example" ]