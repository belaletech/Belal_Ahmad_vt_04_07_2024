# Assignment 1 - URL Shortener

## Project Overview
This project implements a URL Shortener service that takes a long URL and generates a shorter, unique alias. When the short link is accessed, the service redirects to the original URL. The application is designed to be highly available with minimal latency and ensure that the shortened links are not predictable. Links will expire after a default period of 10 months

## Key Features
- Shorten a long URL to a unique alias.
- Redirect to the original URL when the short link is accessed.
- Set a default expiration period of 10 months for each link.
- Ensure high availability to prevent downtime of URL redirections.
- Minimize latency for real-time URL redirection.
- Generate unpredictable short URLs.
- Short URL length up to 30 characters, starting with prefix: `http://localhost:8081/`.

## Assumptions
- Expected to handle 5M new URL shortenings per month.
- Expected 100:1 read/write ratio.

## Technologies Used
- **Spring Boot** for building the application.
- **PostgreSQL** as the database for storing URL mappings.

## API Endpoints

1. **POST** `/shorten`
    - **Description**: Shorten a given destination URL.
    - **Request Body**: `{ "destinationUrl": "http://example.com" }`
    - **Response**: `{ "shortUrl": "http://localhost:8081/abc123", "id": 1 }`
    - **Success**: 200 OK
    - **Failure**: 400 Bad Request

2. **POST** `/update`
    - **Description**: Update the destination URL for a given short URL.
    - **Request Body**: `{ "shortUrl": "http://localhost:8081/abc123", "destinationUrl": "http://newexample.com" }`
    - **Response**: `{ "success": true }`
    - **Success**: 200 OK
    - **Failure**: 400 Bad Request

3. **GET** `/get`
    - **Description**: Get the destination URL for a given short URL.
    - **Request Params**: `shortUrl=http://localhost:8081/abc123`
    - **Response**: `{ "destinationUrl": "http://example.com" }`
    - **Success**: 200 OK
    - **Failure**: 404 Not Found

4. **POST** `/updateExpiry`
    - **Description**: Update the expiration date for a given short URL.
    - **Request Body**: `{ "shortUrl": "http://localhost:8080/abc123", "daysToAdd": 30 }`
    - **Response**: `{ "success": true }`
    - **Success**: 200 OK
    - **Failure**: 400 Bad Request
5. **IMAGE** `Scrren-shot of postGreSQL`
   ![Alt text](image/img.png)

   


