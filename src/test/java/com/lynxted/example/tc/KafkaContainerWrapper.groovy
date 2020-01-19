package com.lynxted.example.tc

import org.testcontainers.containers.KafkaContainer

class KafkaContainerWrapper extends KafkaContainer {

    private static KafkaContainerWrapper CONTAINER

    private KafkaContainerWrapper(String confluentPlatformVersion) {
        super(confluentPlatformVersion)
    }

    static KafkaContainer getContainer() {
        if (CONTAINER == null) {
            CONTAINER = new KafkaContainerWrapper("5.3.0")
            CONTAINER.start()
            CONTAINER.setupSpringProperties()
        }
        CONTAINER
    }

    @Override
    void close() {
        super.close()
    }

    void setupSpringProperties() {
        String address = kafkaContainerAddress()
        setupBrokerAddress(address)
    }

    private String kafkaContainerAddress() {
        CONTAINER.getBootstrapServers()
    }

    private void setupBrokerAddress(String address) {
        System.setProperty("spring.kafka.bootstrap-servers", address)
    }
}