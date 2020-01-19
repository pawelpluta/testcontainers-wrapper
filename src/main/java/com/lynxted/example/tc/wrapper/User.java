package com.lynxted.example.tc.wrapper;

class User {

    private static final User EMPTY = new User(null, null, null);
    private final UserId id;
    private final String firstName;
    private final String lastName;

    private User(UserId id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    static User empty() {
        return EMPTY;
    }

    User after(UserRegistered event) {
        return new User(UserId.of(event.getUserId()), event.getFirstName(), event.getLastName());
    }

    UserId getId() {
        return id;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }
}
