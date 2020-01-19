package com.lynxted.example.tc.wrapper;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
class UserEventListener {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    UserEventListener(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "user-data")
    void userDataListener(String eventData) {
        UserRegistered event = eventFrom(eventData);
        userRepository.save(User.empty().after(event));
    }

    private UserRegistered eventFrom(String eventData) {
        try {
            return objectMapper.readValue(eventData, UserRegistered.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not deserialize event", e);
        }
    }
}
