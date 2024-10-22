# Text Analyzer Backend

## Overview
This backend service provides text analysis features such as sentiment analysis and word frequency counting. It is built using Spring Boot, MongoDB, and Stanford CoreNLP for natural language processing.

## Key Features
- **Sentiment Analysis**: Analyze the sentiment of a given text (Positive, Negative, Neutral).
- **Word Frequency Analysis**: Analyze and calculate the frequency of each word in the given text.
- **MongoDB Integration**: Store and retrieve text analysis history.

## Prerequisites
- Java 21
- Maven
- Docker
- MongoDB (as a Docker container or installed locally)
- Stanford CoreNLP Models (make sure the required models are properly installed)

## Installation
1. **Clone the Repository**
    ```sh
    git clone https://github.com/yourusername/text-analyzer.git
    cd text-analyzer
    ```

2. **Install Dependencies**
    You can install the required Maven dependencies by running:
    ```sh
    mvn clean install
    ```

3. **Stanford CoreNLP Configuration**
    Download the Stanford CoreNLP models for NLP tasks:
    [Stanford CoreNLP Models Download Page](https://stanfordnlp.github.io/CoreNLP/download.html)
    Place the models in a folder that your application can access. Then, configure the path in your Spring Boot application configuration (usually in `application.properties`):
    ```properties
    opennlp.models.path=/path/to/corenlp/models
    ```

4. **MongoDB Setup**
    Make sure MongoDB is running, either locally or using Docker.
    To start MongoDB with Docker, run the following command:
    ```sh
    docker run -d -p 27017:27017 --name mongodb mongo
    ```

5. **Running the Application**
    Once everything is set up, you can run the Spring Boot application:
    ```sh
    mvn spring-boot:run
    ```
   



## API Endpoints

### 1. Analyze Text
Analyze the sentiment and word frequency of a given text.

- **URL**: `/textAnalysis/analyze`
- **Method**: `POST`
- **Content-Type**: `application/json`
- **Request Body**:
  ```json
  {
    "text": "Your text goes here."
  }
  
    ```
  
Get all analysis history entries.

- **URL**: `/textAnalysis/getAllResults`
- **Method**: `GET`
- **Response**:
  ```json
  [
    {
        "id": "6717ed442eb7cb0f37b77724",
        "text": "Hello world! Hello everyone.",
        "language": "eng",
        "sentiment": null,
        "wordFrequency": "Word frequency : \nworld : 1\nHello : 2\neveryone : 1\n"
    }
  ]
  ```

To set up both MongoDB and the Spring Boot backend application using Docker, follow these steps:

1. **Create a `docker-compose.yml` file** to spin up both the Spring Boot backend and MongoDB:
    ```yaml
    version: '3'
    services:
      mongodb:
        image: mongo
        ports:
          - "27017:27017"

      backend:
        build: .
        ports:
          - "8080:8080"
        depends_on:
          - mongodb
        environment:
          - SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/text-analyzer
    ```

3. **Start everything with Docker Compose**:
    ```sh
    docker-compose up -d
    ```
