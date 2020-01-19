package com.lynxted.example.tc.wrapper;

import java.util.Objects;

class UserId {
    private final String value;

    private UserId(String value) {
        this.value = value;
    }

    static UserId of(String value) {
        return new UserId(value);
    }

    String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserId userId = (UserId) o;
        return Objects.equals(value, userId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
