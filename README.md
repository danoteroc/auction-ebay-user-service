# Auction-Ebay User Management Service
User service for the auction ebay (topics in SWE) project.

The application has environment variables set for retrieving the cognito pool. 

This variables have to be set in the file ".env":

```
AWS_COGNITO_CLIENT_ID=xxx
AWS_COGNITO_TOKEN_SIGNING_KEY=yyy
```

### Run using docker
```
docker build -t user-service:latest .
docker run -p 8080:8080 user-service:latest
```

### Run locally

Run the application using the 
[maven wrapper](https://www.baeldung.com/maven-wrapper):

```
./mvnw spring-boot:run
```

