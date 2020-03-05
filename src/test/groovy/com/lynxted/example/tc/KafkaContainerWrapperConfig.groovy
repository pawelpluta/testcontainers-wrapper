package com.lynxted.example.tc

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaContainerWrapperConfig {
    private static final KafkaContainerWrapper containerWrapper = KafkaContainerWrapper.getContainer()

    @Bean
    NewTopic userData() {
        return new NewTopic("user-data", 1, (short) 1)
    }

}
