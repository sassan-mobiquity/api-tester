# Code Assignment Solution

#### Author: Sassan Sadeghi

This is the solution to Mobiquity's code assignment for the "Senior QA Engineer" role. Inside, there is a small framework that runs automated tests against the REST API of the Typicode JSON Placeholder application. The functional tests are written in Given-When-Then format inside Cucumber feature files. Please follow the rest of this document for instructions on how to install the framework, run the tests and use the generated test report.
 


## Tech Stack
Here's the list of the main tools used:
-  **Java - OpenJDK 14.0.1**
-  **Gradle 6.3**
-  **TestNG**
-  **REST Assured**
-  **Cucumber**
-  **Cucumber-reporting**
-  **Lombok** (uses annotations to generate code during compilation; helps get read of repetitive code for setters, getters, constructors, etc.)
-  **SLF4J** (robust and simple logging tool)
-  **Typesafe Config**






## Install, Configure and Run

Use this link to download OpenJDK 14.0.1 or a later stable version: https://jdk.java.net/archive/. Make sure you set the JAVA_HOME environment variable to the path of your unpacked JDK directory. Then add *JAVA_HOME/bin* (backslash for Windows) to your PATH variable. Open a terminal and run the following to make sure java is installed:
 
```bash
java -version
```

You shouldn't need to update the configuration file but if you need to change the application URL or the http request timeout, please go to *src/test/resources/local-config.conf* and update the parameter values.

There's no need to install Gradle on you machine. Simply open a terminal on the same directory as this document and make sure the terminal has access to internet. Then run the following command:

```bash
gradlew clean build
```

There is a .bash script for Windows and a Shell script for Linux which after this command, will try to download gradle 6.3. The scripts use JAVA_HOME variable to set the jvm for the installed gradle agent. Finally, gradle will build the project and execute the tests.

## Test Reports
After each round of execution, a html report is generated at the */target/* directory. Test reports are labeled with system timestamp and archived in that directory. Go to any report directory and open the html file at this path: *cucumber-html-reports/overview-features.html*. This is a powerful reporting tool that exposes all sorts of information about test results. Please refer to their GitHub page for further information: https://github.com/damianszczepanik/cucumber-reporting  

## Navigating the project
The project structure is a standard gradle project. All the test code can be found in *src/test/java* and all the Cucumber feature files are located in *src/test/resources/features*. 
<br>To run the tests on an IDE, go to *src/test/java/com.mobiquity.assignment.tests* and execute the TestNG test runner. For most IDEs like IntelliJ, you need to at least install the TestNG and Lombok plugins. Lombok also requires you to enable annotation processing in your IDE. 
<br> You can also find the cucumber step definitions at *src/test/java/com.mobiquity.assignment.steps*. Step definitions are typically created based on business domains. My assumption was that the target application has two main domains: 1- users 2- posts (comments are also included in this domain).
<br> The *post-comments.feature* file is a data driven scenario. There's an extra row in the data table that is commented out. If un-commented, that row will lead to a test iteration which will search for a non-existing username, leading to a test failure. Feel free to use that iteration to see how the test reports reflect failed tests. 


## CircleCI
A pipeline file is provided at *.circleci/config.yml*. You can use this file to build and run the tests on CircleCI. After execution, the html test reports will be published as a tarball in the build artifacts.