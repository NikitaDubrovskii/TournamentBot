FROM maven:3.9.8-amazoncorretto-21-al2023 AS builder

WORKDIR /app

COPY pom.xml .
COPY starter/pom.xml starter/pom.xml
COPY action/pom.xml action/pom.xml
COPY configuration/pom.xml configuration/pom.xml
COPY model/pom.xml model/pom.xml
COPY repository/pom.xml repository/pom.xml
COPY service/pom.xml service/pom.xml
COPY telegram/pom.xml telegram/pom.xml

RUN mvn dependency:go-offline

COPY . .

RUN mvn package

FROM openjdk:21-jdk

WORKDIR /app

COPY --from=builder /app/starter/target/starter-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "--enable-preview", "app.jar"]