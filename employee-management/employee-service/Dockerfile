# Use a Maven base image with Eclipse Temurin 21 (Java 21) for the build stage
FROM maven:3.9.9-eclipse-temurin-21 as builder

# Set the working directory inside the container to /app
WORKDIR /app

# Copy the pom.xml file from the local machine to the /app directory inside the container
COPY pom.xml .

# Run the 'nvm dependency:go-offline' command to download dependencies offline (with '-B' for batch mode)
# This step ensures that Maven dependencies are fetched and available for offline builds
RUN mvn dependency:go-offline -B

# Copy the source code from the local machine's 'src' directory to the /app/src directory inside the container
COPY src ./src

# Run 'nvm clean package' to build and package the project, cleaning any existing artifacts
# This command will create the compiled artifacts like .jar files in the target directory
RUN mvn clean package

# Use OpenJDK 21 image for the runtime (runner) stage
FROM openjdk:21-jdk AS runner

# Set the working directory inside the container for the runtime stage
WORKDIR /app

# Copy the generated JAR file from the 'builder' stage to the runtime stage
# The JAR file is expected to be in the target directory, adjust the filename as necessary
COPY --from=builder ./app/target/employee-service-0.0.1-SNAPSHOT.jar ./app.jar

# Expose port 4000 for the application to listen on
EXPOSE 4000

# Define the command to run the application. Note: the correct format is ["java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]
