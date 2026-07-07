# Java Test Automation Project

This project demonstrates Web UI automation, REST API automation, and performance testing using Java-based testing tools.

## Project Objectives

The project covers the following tasks:

- Automating an e-commerce login process with Selenium WebDriver
- Testing REST API endpoints with REST Assured
- Creating a dummy JSON API endpoint using Nginx
- Performing stress testing with Apache JMeter
- Validating status codes, response bodies, and response times

## Technologies

- Java 17
- Apache Maven 3.9.16
- Selenium WebDriver
- JUnit
- REST Assured
- JSONPath
- Google Chrome
- Nginx
- Apache JMeter
- Visual Studio Code

## Project Structure

```text
ecommerce-automation/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       └── java/
│           └── com/
│               └── testautomation/
│                   ├── LoginTest.java
│                   └── ApiTest.java
├── jmeter/
│   └── nginx-stress-test.jmx
├── nginx-api/
│   └── test.json
├── pom.xml
├── .gitignore
└── README.md
```

## Web UI Automation

The Selenium test automates the login process of the SauceDemo e-commerce testing website.

### Automated Steps

1. Launch Google Chrome.
2. Navigate to the SauceDemo login page.
3. Locate the username field using its ID.
4. Enter the test username.
5. Locate the password field using its ID.
6. Enter the test password.
7. Click the login button.
8. Verify that the inventory page is opened successfully.

### Test Website

```text
https://www.saucedemo.com/
```

### Test Credentials

```text
Username: standard_user
Password: secret_sauce
```

### Selenium Locators

```java
By.id("user-name")
By.id("password")
By.id("login-button")
```

The test verifies the successful login by checking whether the resulting URL contains:

```text
inventory.html
```

## REST API Automation

REST Assured was used to automate GET and POST requests against the JSONPlaceholder API.

### Base URI

```text
https://jsonplaceholder.typicode.com
```

### GET Test

The GET test sends a request to:

```text
/posts/1
```

The following values are validated:

- HTTP status code is `200 OK`
- Response time is below `5000 ms`
- `id` is equal to `1`
- `userId` is equal to `1`
- `title` is not empty

### POST Test

The POST test sends a JSON request to:

```text
/posts
```

Example request body:

```json
{
  "title": "Test Automation",
  "body": "REST Assured POST request",
  "userId": 1
}
```

The following values are validated:

- HTTP status code is `201 Created`
- Response time is below `5000 ms`
- Returned title matches the submitted title
- Returned body matches the submitted body
- Returned user ID is equal to `1`
- Returned resource ID is equal to `101`

## Nginx Dummy API

A local Nginx server was configured to return a mock JSON response.

### Dummy Endpoint

```text
http://localhost/api/test.json
```

The JSON file is located at:

```text
nginx-api/test.json
```

Example response:

```json
{
  "status": "success",
  "message": "Nginx dummy API is working",
  "service": "performance-test",
  "version": 1
}
```

To use the dummy endpoint, copy `test.json` into the following directory of the local Nginx installation:

```text
nginx/html/api/test.json
```

## Apache JMeter Stress Testing

Apache JMeter was used to send concurrent requests to the local Nginx dummy API.

### Test Configuration

| Setting | Value |
|---|---:|
| Number of Threads | 100 |
| Ramp-up Period | 10 seconds |
| Loop Count | 10 |
| Expected Requests | 1000 |
| HTTP Method | GET |
| Target Endpoint | `/api/test.json` |
| Expected Status Code | 200 |

The JMeter test plan is located at:

```text
jmeter/nginx-stress-test.jmx
```

The stress test completed with an error rate of `0.00%`.

## Prerequisites

Install the following tools before running the project:

- Java Development Kit 17
- Apache Maven
- Google Chrome
- Nginx
- Apache JMeter

Check Java and Maven installations:

```bash
java -version
javac -version
mvn -version
```

## Running the Tests

Open a terminal in the project directory.

### Run All Maven Tests

```bash
mvn clean test
```

### Run Only the Selenium Login Test

```bash
mvn "-Dtest=LoginTest" test
```

### Run Only the REST Assured API Tests

```bash
mvn "-Dtest=ApiTest" test
```

A successful execution should display:

```text
BUILD SUCCESS
```

## Test Results

The completed tests demonstrated that:

- Selenium successfully automated the SauceDemo login process.
- The successful login was verified with an assertion.
- The GET API test returned `200 OK`.
- The POST API test returned `201 Created`.
- API response times remained below the configured boundary.
- Nginx successfully returned the dummy JSON response.
- JMeter completed the stress test without request errors.

## Author

Developed as part of a Java test automation assignment.
