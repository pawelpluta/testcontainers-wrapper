# Testcontainer Wrapper Sample
Sample application that shows, how to create Testcontainer and configure Spring application with container-provided properties.

Presents, how to manually manage Testcontainer instance and add Spring Boot application properties with values obtained from created container. Presented solution can be generally used with other frameworks, or even with plain Java application. Using Testcontainer `GenericContainer` instead of `KafkaContainer` allows for creation of container from any Docker Image.
