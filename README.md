Portfolio Management System

This project is a Portfolio Management System that allows users to manage their investment portfolios. It provides functionalities to update market data, track portfolio values, and handle various events related to market changes.
Features

    Market Data Update: Automatically updates market data for securities at regular intervals.
    Portfolio Value Tracking: Tracks the value of the portfolio based on current market prices.
    Event Handling: Listens to market data update events and triggers portfolio value update events accordingly.

Prerequisites

    Java Development Kit (JDK) version 8 or higher
    Gradle for building the project

Setup

    Clone the Repository:

git clone <repository-url>

Build the Project:

cd portfolio-management-system
gradle build

Run the Application:

    java -jar build/libs/portfolio-management-system.jar

    You should see the output in the terminal/command line

    Access the Application:
    Once the application is running, you can access it at http://localhost:8080.

Configuration

    Market data update interval, database connection details, and other configurations can be modified in the application.properties file located in the src/main/resources directory.
