# Mail service

This is Mail service for [Qaprotours](https://github.com/ilisau/qaprotours).

This service uses Reactive Postgres Client for database interaction and provides Reactive approach.

This application provides:

1) Resilience4j Circuit Breaker

We use Circuit Breaker to wrap function calls and return default data instead
of throwing exceptions if supplier service is unavailable. This approach helps to avoid falling of request.
After some requests are fallen, the status of breaker is changed and custom handling is used until supplier can't
respond to requests.

You can change Circuit Breaker configuration in ```resilience4j.circuitbreaker``` property.

2) Kafka

We use Kafka here for receiving messages from other clients instead of blocking rest approach.

## Installation

You need to pull the project from the repository and install the dependencies.

This service by default use the following variables from .env file:

1. 8082 - port for the service itself
2. `SPRING_MAIL_HOST` - host of the mail server
3. `SPRING_MAIL_PORT` - port of the mail server
4. `SPRING_MAIL_USERNAME` - username of mail sender
5. `SPRING_MAIL_PASSWORD` - password of mail sender
6. `KAFKA_HOST` - host with port for Kafka bootstrap server
7. `ACTIVATION_LINK` - URL to be opened from email to activate account
8. `RESTORE_LINK` - URL to be opened from email to restore password

To run application you need to run
```console
 sh run.sh
 ```