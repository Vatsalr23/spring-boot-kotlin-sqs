# spring-boot-kotlin-sqs-demo

This is a demo spring boot application written in Kotlin to integrate with AWS SQS and play around with it.

## How to the run the application
Update the following variables in `application.properties` (or create `application-local.properties` and add the variables):
```
aws.keys.secret=ADD_ACCESS_KEY
aws.keys.access=ADD_SECRET_KEY
aws.region=DEFAULT_REGION
aws.queue.name=YOUR_QUEUE
```

Note: `application-local.properties` is git ignored.

- Then proceed to run the application from the root folder with: <br>
 `./gradlew bootRun`
- If you want to run it with application-local.properties run with local active profile like so: <br>
 `./gradlew bootRun -Dspring.profiles.active=local`
