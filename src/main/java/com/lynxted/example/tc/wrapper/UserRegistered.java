package com.lynxted.example.tc.wrapper;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class UserRegistered {
    private final String userId;
    private final String firstName;
    private final String lastName;
    private final Instant occurrenceTime;

    @JsonCreator
    private UserRegistered(
            @JsonProperty("userId") String userId,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("occurrenceTime") Instant occurrenceTime) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.occurrenceTime = occurrenceTime;
    }

    String getUserId() {
        return userId;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    Instant getOccurrenceTime() {
        return occurrenceTime;
    }
}
