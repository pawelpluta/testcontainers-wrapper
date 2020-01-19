package com.lynxted.example.tc.wrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
class UserRepository {

    private final Map<UserId, User> idToUserMap;

    UserRepository() {
        this.idToUserMap = new HashMap<>();
    }

    void save(User userToBeSaved) {
        idToUserMap.put(userToBeSaved.getId(), userToBeSaved);
    }

    Optional<User> findById(UserId userId) {
        return Optional.ofNullable(idToUserMap.get(userId));
    }
}
