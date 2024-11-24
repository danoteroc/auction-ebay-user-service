# Auction-Ebay User Management Service
User service for the auction ebay (topics in SWE) project.

The application has environment variables set for retrieving the cognito pool as follows:

```
AWS_COGNITO_CLIENT_ID=xxx
AWS_COGNITO_TOKEN_SIGNING_KEY=yyy
```
**note**: these are meant to be set inside the container
After setting the environment variables, run the application using the 
[maven wrapper](https://www.baeldung.com/maven-wrapper):

```
./mvnw spring-boot:run
```

