### Project Description

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

### Notes

- This project uses a H2 database as a temp file.
- Current configuration is for running this application locally, to configure the application for a different type of
  environment, you'll need to add/edit some config e.g things related to docker networking. Also, you could utilize
  spring profiles along with maven profiles to create different configurations based on your needs.

### Technologies

- Java 11
- Spring Boot
- Maven
- Docker

### Deployment

To Execute the springboot application please the below docker compose file:

First, navigate to this docker-compose file

```
- docker-compose up -d
```

To stop the container

```
-  docker-compose down
```

open your browser on the home page of swagger :

(http://127.0.0.1:8080/swagger-ui.html/)
