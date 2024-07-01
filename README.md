# Automated Testing of Geolocation API using RestAssured
This project demonstrates automated testing of Geolocation API using RestAssured framework with Java. It includes setup instructions and examples to run tests using Maven.

## Prerequisites

- Java JDK (8 or above)
- Maven

## Setup

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/kartik1o/GeolocationApiAutomation.git
   cd GeolocationApiAutomation

## Set Google Geolocation API Key

1. **Obtain a Google Geolocation API Key:**
    - Obtain a Google Geolocation API key from [Google Cloud Console](https://developers.google.com/maps/documentation/geolocation/get-api-key).

2. **Make sure to replace your API key in `config.properties` under src/test/resources**


# Project Structure

- **src/test/java**: Contains test classes, utilities, payloads, and endpoints for API automation.
- **src/test/resources**: Contains configuration properties files.
- **pom.xml**: Maven build file defining dependencies, plugins, and profiles.

# Running Tests

To execute the automated tests for this project using Maven, follow these steps:

1. **Open a Terminal or Command Prompt:**
   Ensure you are in the root directory of your Maven project.

2. **Run Maven Test Command:**
   Use the following command to trigger the test execution:

3. **Prior to running tests, make sure your system has maven installed, and you have replaced your API key in `config.properties` file.
   ```bash
   mvn test

# Test Structure

The automated tests are organized using the `GeolocationApiTest.java` class located in `src/test/java/api/test`. This test class demonstrates the usage of RestAssured to interact with the Google Geolocation API, validating different scenarios:

- **Valid Request with Coordinates:**
    - This test case verifies the API's response when sending a valid request with coordinates, ensuring correct handling of location data.

- **Request without API Key:**
    - This scenario tests how the API behaves when a request is made without providing the required API key. It validates the expected error response or behavior.

- **Invalid Request Parameters:**
    - Tests are included to validate the API's response to requests with invalid parameters, such as incorrect data formats or missing required fields.

## GeolocationApiTest.java

This example test class showcases best practices for:
- Setting up API requests using RestAssured.
- Defining assertions to validate expected responses.
- Organizing test cases to cover various use cases and edge scenarios of the Google Geolocation API.

Feel free to explore `GeolocationApiTest.java` to understand how each test case is implemented and how RestAssured facilitates interaction with the API endpoints. These tests help ensure the reliability and correctness of API interactions within your project.

# Dependencies

This project utilizes several dependencies to facilitate automated testing of the Google Geolocation API using RestAssured with Java and Maven:

- **RestAssured**: A Java library specifically designed for API testing. It simplifies the interaction with HTTP and HTTPS endpoints and supports assertions to validate responses.

- **TestNG**: A testing framework used for organizing and running test cases. It provides annotations, assertions, and reporting features that streamline the testing process.

- **Maven Surefire Plugin**: A Maven plugin responsible for executing tests during the build process. It integrates seamlessly with Maven projects, running test classes and generating reports on test results.

# Reporting

After running automated tests using Maven, detailed test reports are automatically generated and stored in the `target/surefire-reports` directory of your project. To view comprehensive test results:

1. **Locate the Test Reports:**
    - Navigate to the `target/surefire-reports` directory in your project structure.

2. **Open `index.html` in a Web Browser:**
    - Locate and open the `index.html` file within the `target/surefire-reports` directory using a web browser of your choice.

