# TestContainer Wrapper Sample
Sample application that shows, how to create Test Container and configure Spring application with container-provided properties.

Presents, how to manually manage Test Container instance and add Spring Boot application properties with values obtained from created container. Presented solution can be generally used with other frameworks, or even with plain Java application. Using Test Container `GenericContainer` instead of `KafkaContainer` allows for creation of container from any Docker Image.
