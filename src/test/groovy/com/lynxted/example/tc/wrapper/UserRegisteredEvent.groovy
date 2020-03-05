package com.lynxted.example.tc.wrapper

import java.time.Instant

import static java.time.Instant.now

class UserRegisteredEvent {

    private String userId
    private String firstName
    private String lastName
    private Instant occurrenceTime = now()

    UserRegisteredEvent withUserId(String userId) {
        this.userId = userId
        this
    }

    UserRegisteredEvent withFirstName(String firstName) {
        this.firstName = firstName
        this
    }

    UserRegisteredEvent withLastName(String lastName) {
        this.lastName = lastName
        this
    }

    String eventBody() {
        """
            {
                "type" : "USER_REGISTERED",
                "userId" : "${userId}",
                "firstName" : "${firstName}",
                "lastName" : "${lastName}",
                "occurrenceTime" : "${occurrenceTime}"
            }
        """
    }


}
