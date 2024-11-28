# Auction-Ebay User Management Service
User service for the auction ebay (topics in SWE) project.

The application has environment variables set for retrieving the cognito pool. 

These variables have to be set in the file ".env":

```
AWS_COGNITO_CLIENT_ID=xxx
AWS_COGNITO_USERPOOL=yyy
```

Additionally, you should have a profile in your .aws/credentials file named "auction-project" with
an access key id and a secret access key to Kyle's User Pool in Cognito:
```
[default] 
aws_access_key_id = ...
aws_secret_access_key = ...

[auction-project]
aws_access_key_id = ...
aws_secret_access_key = ...
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

