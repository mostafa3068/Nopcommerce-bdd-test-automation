# 🧪 Nopcommerce BDD Test Automation

## 📌 Project Overview

This project is a UI test automation framework built for the nopCommerce e-commerce application.  
It automates key user journeys such as registration, login, product search, cart management, coupon validation, and checkout.

The framework was designed using a structured automation architecture based on BDD, Page Object Model, Selenium WebDriver, Cucumber, TestNG, and Maven.

The goal of this project is not only to automate test cases, but also to demonstrate how a maintainable, scalable, and professional test automation framework can be designed from the ground up.

---

## 🎯 Project Goal

This project was created to practice and demonstrate real-world QA automation engineering skills, including:

- Building a reusable Selenium automation framework
- Applying Page Object Model design
- Writing business-readable BDD scenarios
- Managing browser lifecycle safely
- Handling waits and dynamic elements
- Organizing smoke, regression, and negative tests
- Generating professional test reports
- Preparing the framework for CI/CD integration

---

## 🛠️ Technologies Used

- Java 17
- Selenium WebDriver
- Cucumber
- TestNG
- Maven
- AssertJ
- WebDriverManager
- Selenium Manager
- PageFactory
- Gherkin
- Git / GitHub

---

## ✨ Key Features

This framework supports:

- Automated UI testing for nopCommerce
- BDD scenarios using Gherkin syntax
- Page Object Model implementation
- Reusable page actions through a BasePage layer
- Centralized WebDriver management
- Thread-safe execution using ThreadLocal WebDriver
- Smoke, regression, and negative test execution
- Scenario hooks for setup and teardown
- Screenshot capture on test failure
- Configurable browser and environment settings
- Cucumber HTML, JSON, XML, and timeline reports
- Failed scenario rerun support
- TestNG suite configuration
- Parallel execution support

---

## 🧠 Development Process

The project was built with a framework-first mindset rather than writing isolated automation scripts. The main focus was to create a structure that is easy to maintain, extend, debug, and execute in different environments.

The development process included:

- Analyzing the main business flows of the nopCommerce application
- Identifying core user journeys such as registration, login, search, cart, coupon, and checkout
- Designing the framework using BDD to make test scenarios readable and business-friendly
- Applying the Page Object Model to separate page locators and actions from test logic
- Creating a BasePage layer to centralize common Selenium actions such as clicking, typing, reading text, and navigation
- Building a DriverFactory to manage WebDriver creation, browser setup, and cleanup from one place
- Using ThreadLocal WebDriver to prepare the framework for safe parallel execution
- Adding configuration management through `config.properties` to avoid hardcoding browser, URL, timeout, and credential values
- Creating reusable utility classes for waits, configuration reading, and browser management
- Organizing test execution using different Cucumber/TestNG runners for smoke, regression, negative, failed, and full test suites
- Adding hooks to handle scenario setup, teardown, screenshots on failure, and driver cleanup
- Generating multiple report formats to support local debugging and future CI/CD integration

This process helped transform the project from simple UI automation into a structured test automation framework.

---

## 📈 Personal Growth From This Project

This project helped me grow from writing basic automation steps into thinking like a framework designer.

Through this project, I improved my understanding of:

- Designing scalable automation frameworks
- Structuring Selenium projects professionally
- Applying Page Object Model in a practical project
- Writing readable BDD scenarios with Cucumber
- Separating test logic from UI interaction logic
- Managing WebDriver lifecycle safely
- Handling dynamic web elements using explicit waits
- Organizing tests into smoke, regression, and negative suites
- Generating and reading automation test reports
- Preparing an automation project for CI/CD usage
- Thinking about maintainability, reusability, and long-term project growth

The biggest growth from this project was learning how to build automation with structure, not just scripts.

---

## 🚀 Future Improvements

This framework can be improved further by adding more advanced engineering and DevOps practices, such as:

- Adding GitHub Actions pipeline for CI execution
- Adding Jenkins pipeline support
- Running smoke tests automatically on every pull request
- Running regression tests automatically on scheduled builds
- Supporting command-line environment overrides such as browser, base URL, and headless mode
- Adding headless browser execution for CI environments
- Adding Docker support to run the application and tests in isolated containers
- Adding Allure reports for more advanced reporting and visualization
- Adding API testing coverage for backend validation
- Adding database validation for end-to-end test verification
- Adding cross-browser execution matrix for Chrome, Firefox, and Edge
- Adding retry logic for flaky test handling
- Improving test data management using external files or data factories
- Adding more edge-case and negative test scenarios
- Adding architecture diagrams and extended technical documentation

These improvements would make the framework more production-ready and closer to enterprise-level automation practices.

---

## 🎥 Project Demo Video

A demo video showing the framework execution.

https://github.com/user-attachments/assets/c344699f-efb0-48d9-9e0e-add81418f592

---

## 📥 Download and Run the Project

### Prerequisites

Before running the project, make sure the following tools are installed:

- Java 17
- Maven
- Git
- Google Chrome
- Docker Desktop
- nopCommerce application running locally or on a test environment

The default application URL used by the framework is:

```text
http://localhost:5000
```

You can change the application URL from:

```text
src/test/resources/config.properties
```
## 🐳 Local nopCommerce Deployment with Docker

This project uses Docker to run the nopCommerce application locally as a test environment.

The Docker setup includes:

- `nopcommerce` web application container
- `sqlserver` database container
- Port mapping for accessing the application locally
- Isolated local environment for executing UI automation tests

After starting the Docker containers, the nopCommerce website should be available at:

```text
http://localhost:5000
```



### Verify Docker Desktop Is Running

Open Docker Desktop and make sure the Docker engine is running.

You should see the nopCommerce containers running, including:

```text
nopcommerce
sqlserver
```

Example Docker containers:

```text
nopcommerce-docker
├── nopcommerce
└── sqlserver
```

---

### Start the nopCommerce Environment

If the project includes a `docker-compose.yml` file, start the application using:

```bash
docker compose up -d
```

Or, for older Docker versions:

```bash
docker-compose up -d
```

This command starts the nopCommerce application and SQL Server database in the background.



### Check Running Containers

To verify that the containers are running, use:

```bash
docker ps
```

You should see containers for:

```text
nopcommerce
sqlserver
```



### Open the Application

After the containers are running, open the website in your browser:

```text
http://localhost:5000
```

Make sure the website loads successfully before running the automation tests.



### Stop the Docker Environment

To stop the running containers, use:

```bash
docker compose down
```

Or:

```bash
docker-compose down
```



## 📦 Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/Nopcommerce-bdd-test-automation.git
cd Nopcommerce-bdd-test-automation
```



## ⚙️ Install Dependencies

Maven will automatically download the required dependencies from `pom.xml` when you run the tests.

You can also validate the project setup using:

```bash
mvn clean compile
```



### Run All Tests

```bash
mvn clean test -Dtest=TestRunner
```



### Run Smoke Tests

```bash
mvn clean test -Dtest=SmokeTestRunner
```



### Run Regression Tests

```bash
mvn clean test -Dtest=RegressionTestRunner
```



### Run Negative Tests

```bash
mvn clean test -Dtest=NegativeTestRunner
```



### View Test Reports

After execution, reports are generated under:

```text
target/cucumber-reports
```

The main HTML report can be found at:

```text
target/cucumber-reports/cucumber.html
```

---

## 🏛️ Architecture Explanation

- Feature files describe the expected application behavior in readable Gherkin syntax.
- Step definitions connect the Gherkin steps to Java automation code.
- Page objects contain locators and actions for each application page.
- BasePage provides reusable Selenium actions such as click, type, get text, and navigation.
- Utility classes manage configuration, waits, and WebDriver lifecycle.
- Selenium WebDriver interacts with the browser.

  ---

 ## 📁 Project Structure

```text
nopcommerce-automation
├── src
│   ├── main
│   │   └── java
│   │       └── mostafa.qc
│   │           ├── constants
│   │           │   └── Constants.java
│   │           ├── pages
│   │           │   ├── BasePage.java
│   │           │   ├── HomePage.java
│   │           │   ├── LoginPage.java
│   │           │   ├── RegistrationPage.java
│   │           │   ├── SearchResultsPage.java
│   │           │   ├── ProductPage.java
│   │           │   ├── CartPage.java
│   │           │   └── CheckoutPage.java
│   │           └── utils
│   │               ├── ConfigReader.java
│   │               ├── DriverFactory.java
│   │               └── WaitUtils.java
│   │
│   └── test
│       ├── java
│       │   └── mostafa.qc
│       │       ├── context
│       │       │   └── TestContext.java
│       │       ├── listeners
│       │       │   └── TestListener.java
│       │       ├── runners
│       │       │   ├── TestRunner.java
│       │       │   ├── SmokeTestRunner.java
│       │       │   ├── RegressionTestRunner.java
│       │       │   ├── NegativeTestRunner.java
│       │       │   └── FailedTestRunner.java
│       │       └── stepdefinitions
│       │           ├── Hooks.java
│       │           ├── LoginSteps.java
│       │           ├── RegistrationSteps.java
│       │           ├── SearchSteps.java
│       │           ├── CartSteps.java
│       │           ├── CheckoutSteps.java
│       │           └── CouponSteps.java
│       │
│       └── resources
│           ├── config.properties
│           ├── features
│           │   ├── login.feature
│           │   ├── registration.feature
│           │   ├── search.feature
│           │   ├── cart.feature
│           │   ├── checkout.feature
│           │   └── coupon.feature
│           └── testing
│               ├── testng.xml
│               ├── smoke-suite.xml
│               └── regression-suite.xml
│
├── pom.xml
└── README.md

```

---

## 🗂️ Folder Responsibilities

| Folder | Responsibility |
| :--- | :--- |
| `features` | Contains BDD test scenarios written in Gherkin |
| `stepdefinitions` | Maps feature file steps to Java methods |
| `pages` | Stores page objects, locators, and page actions |
| `utils` | Contains reusable framework utilities |
| `constants` | Stores shared constant values |
| `runners` | Controls Cucumber/TestNG execution |
| `testing` | Stores TestNG suite XML files |
| `context` | Stores shared scenario-level state |
| `listeners` | Handles TestNG execution events |


---

## 🏗️ Design Patterns & Architecture

### 📄 Page Object Model (POM)
Each web page is represented by a dedicated Java class that encapsulates its locators and actions (e.g., `LoginPage.java`, `CartPage.java`, `CheckoutPage.java`). 
* **Benefit:** Centralizes UI interactions, keeping tests clean and dramatically reducing maintenance effort when UI elements change.



### 🧱 Base Page Abstraction
`BasePage.java` serves as the parent class for all page objects, providing centralized wrapper methods for Selenium interactions:
* `click(By locator)`
* `type(By locator, String text)`
* `getText(By locator)`
* `isDisplayed(By locator)`
* `navigateTo(String url)`



### 🏭 Factory Pattern
`DriverFactory.java` centralizes the creation, configuration, and teardown of `WebDriver` instances.
* **Benefit:** Eliminates duplicate driver initialization code across runners and hooks.



### 🧵 ThreadLocal WebDriver
The framework wraps `WebDriver` inside `ThreadLocal<WebDriver>` to support thread-safe parallel test execution.
* **Benefit:** Ensures each concurrent test thread operates independently in its own isolated browser session without state pollution.



### ⚙️ Configuration Management
`ConfigReader.java` dynamically loads key-value pairs from `config.properties`.
* **Benefit:** Externalizes settings like browser type, base URL, and explicit wait timeouts out of source code into a single configuration file.

---

## 🧪 Test Coverage

The framework covers key user journeys across the following nopCommerce modules:

- **User Registration:** Account creation flow and registration validation
- **User Login:** Successful authentication and access control
- **Product Search:** Searching for products and validating search results
- **Product Details:** Navigating to individual product pages and verifying product specifications
- **Shopping Cart:** Adding items to cart, updating quantities, and managing cart items
- **Coupon Application:** Validating discount codes and price adjustments
- **Checkout Flow:** End-to-end checkout execution including billing, shipping, and order completion
- **Negative Login & Validation:** Testing invalid credentials, required field validations, and error messages



