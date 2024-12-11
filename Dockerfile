FROM maven:3.9.1-amazoncorretto-17 AS build
#FROM amazoncorretto:17-alpine AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and any other necessary files
COPY pom.xml  ./
COPY generate_allure_report.bat  ./
COPY src  ./src


## Build the application
RUN mvn clean package  -Dskiptests
#
FROM amazoncorretto:17-alpine

# Set the working directory
WORKDIR /app
#
## Copy the built JAR files from the build stage
COPY --from=build /app/target  /app/target
COPY --from=build /app/src    /app/src


## Run the TestNG tests
CMD java -cp /app/target/frame.jar:/app/target/frame-tests.jar:/app/target/libs/* org.testng.TestNG /app/src/main/resources/properties/testng.xml


