# Automation Testing Framework

## Overview
This is a feature-rich and scalable test automation framework built with **Java**, **Selenium**, **BDD (Behavior-Driven Development)** using **Cucumber**, and **TestNG**. It supports advanced capabilities like parallel test execution, rerunning failed test cases, and data-driven testing to ensure comprehensive test coverage and efficiency.

## Key Features
1. **Behavior-Driven Development (BDD):**
   - Built with **Cucumber**, which allows writing test cases in Gherkin syntax.
   - Gherkin promotes collaboration by using plain English for defining test scenarios.

2. **Test Runner with TestNG:**
   - Uses **TestNG** to manage test execution. TestNG enables grouping, prioritization, parallel execution, and organized test reporting.

3. **Selenium Integration:**
   - Integrates **Selenium WebDriver** for browser automation. Supports testing across multiple browsers.

4. **Advanced Reporting:**
   - Generates beautiful **Extent Reports** with detailed test execution logs, screenshots for failed tests, and execution statuses for each scenario.

5. **Parallel Test Execution:**
   - Configured using **Maven Surefire Plugin**, allowing faster execution by running tests in multiple threads.

6. **Data-Driven Testing:**
   - Testing support using external data sources such as **Excel** (via Apache POI) and JSON files (via the **Jackson Databind** library).

7. **Flexible Configuration:**
   - Well-organized project structure with easy configuration for adding new dependencies, modifying runners, or managing test data.

8. **Logging:**
   - Includes SLF4J for robust and flexible logging.

## Tools & Dependencies
### Core Libraries

Library	Version	Purpose
Java	17	Programming Language
Cucumber	7.16.1	BDD framework for writing Gherkin test scenarios
TestNG	7.10.2	Test execution framework
Selenium WebDriver	4.26.0	Browser automation
Extent Reports	1.14.0	Generate detailed test execution reports
Apache POI	4.1.2	Read and write Excel files
Jackson Databind	2.15.0	JSON processing
SLF4J	2.0.16	Logging framework
JUnit	4.13.2	Additional integration for test behavior (if required)


### Build Tool
- **Maven**: Manages dependencies, builds the project, and supports plugins for parallel execution.

## Project Structure
This project follows a _clean architecture_, segregating responsibilities effectively:
```
AutomationFramework-BDD-TestNG/
├── src/
│   ├── main/
│   │   ├── java/
│   │       ├── Pages/                   # Page classes (e.g., LogIn, BasePage, Register)
│   │       ├── utils/                   # Utility classes (e.g., Config Reader, Locator Reader)
│   │       ├── drivers/                 # WebDriver management classes
│   ├── test/
│   │   ├── java/
│   │   │   ├── StepDefinitions/         # Step Definition classes (Cucumber steps implementation)
│   │   │   ├── TestRunner/              # TestNG or Cucumber runners and FailReRunner
│   │   │   ├── AppHooks/                # Cucumber hooks for pre/post feature setup
│   │   ├── resources/
│   │   │   ├── Features/                # Cucumber feature files written in Gherkin syntax
│   │   │   ├── Locators/                # Stored Locators in JSON files (e.g., XPath, ID)
│   │   │   ├── Reports/                 # Stores test execution reports (Extent Reports)
│   │   │   ├── RerunLog/                # Stores Rerun log if anything fails
│   │   │   └── Config/                  # Stores configuration properties (e.g., URL, Browser)
│
├── pom.xml                              # Maven configuration file
├── .gitignore                           # Git ignore file for excluding unnecessary files from version control
├── README.md                            # Project documentation
```

## Quick Start

### Prerequisites
- Install **Java 17** or later.
- Install a supported browser (e.g., Chrome, Firefox) and the corresponding WebDriver.

### Setup
1. Clone the repository:

   git clone https://github.com/AtharvaBallal/AutomationFramework-BDD-TestNG <br>
   cd AutomationFramework-BDD-TestNG

2. Install dependencies:

   mvn install

3. Configure settings:
    - Update driver paths, URLs, or other configurations in an appropriate configuration file (if available).

### Running Tests
- **Single Feature File Run:** You can run a specific feature file using the corresponding `TestNG` runner.
- **Parallel Execution:** Run the following command to execute tests in parallel using the Maven `Surefire` plugin:

  mvn test


## Reporting
- After test execution, find the **Extent Reports** in the `target/extent-reports/` directory:

  target/extent-reports/

- Reports provide:
    - Execution status of each scenario.
    - Screenshots for failed scenarios.
    - Step-by-step execution logs.

## Parallel Execution
This framework is configured for **parallel execution** to improve performance considerably. You can adjust the thread count or execution mode in the `pom.xml`:
- **Thread Count:**

  <configuration>
      <threadCount>4</threadCount>
  </configuration>

- **Execution Type:** Modify `<parallel>` to `methods` or `classes` as per your requirement.

## Writing Tests
### Creating Feature Files
Define test scenarios in Gherkin syntax inside the `src/test/resources/features/` folder:

Feature: Login functionality

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters a valid username and password
    Then the user should see the dashboard


### Defining Step Definitions
Implement the steps in a corresponding Java class. For example:

@Given("the user is on the login page")
public void userOnLoginPage() {
    
}


### Test Runner
Configure a **TestNG runner** for your test execution in the `src/test/java` directory.

## How to Extend
1. **Add New Features:**
    - Write the `.feature` file in the `features/` folder.
    - Create corresponding step definition files.

2. **Update Reporting:**
    - Customize the Extent Reports library in the runner or configuration classes.

3. **Include Additional Dependencies:**
    - Add Maven dependencies to `pom.xml` as needed.

## Contributing
Contributions are welcome! Please fork the repository, create a branch, and submit a pull request with your changes.

## Contact
For any issues or queries, please contact **Atharva Ballal** at https://github.com/AtharvaBallal.

-------------------------------------------------------------------------------------------------------------------------------------

**Happy Testing!**

