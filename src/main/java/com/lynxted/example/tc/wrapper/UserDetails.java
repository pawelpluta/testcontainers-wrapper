package com.lynxted.example.tc.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

class UserDetails {

    @JsonProperty
    private final String userId;
    @JsonProperty
    private final String displayName;

    UserDetails(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

}
