# Testcontainers Wrapper Sample
This project contains source codes for Medium article [Introduce Any Testcontainer Into Your Spring Application](https://medium.com/vattenfall-tech/introduce-any-testcontainer-into-your-spring-application-d3e21e213baa).

Sample application that shows, how to create Testcontainer and configure Spring application with container-provided properties.

Presents, how to manually manage Testcontainer instance and add Spring Boot application properties with values obtained from created container. Presented solution can be generally used with other frameworks, or even with plain Java application. Using Testcontainers `GenericContainer` instead of `KafkaContainer` allows for creation of container from any Docker Image.
