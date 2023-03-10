# Mail service

This is Mail service for [Qaprotours](https://github.com/ilisau/qaprotours).

This service uses Reactive Postgres Client for database interaction and provides Reactive approach.

## Installation

You need to pull the project from the repository and install the dependencies.

This service by default use the following variables from .env file:

1. 8082 - port for the service itself
2. 8761 - port for the Eureka server
2. `SPRING_MAIL_HOST` - host of the mail server
3. `SPRING_MAIL_PORT` - port of the mail server
4. `SPRING_MAIL_USERNAME` - username of mail sender
5. `SPRING_MAIL_PASSWORD` - password of mail sender
