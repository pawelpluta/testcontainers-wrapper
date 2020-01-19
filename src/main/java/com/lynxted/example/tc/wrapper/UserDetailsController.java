package com.lynxted.example.tc.wrapper;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
class UserDetailsController {

    private final UserRepository userRepository;

    UserDetailsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    List<UserDetails> getByUserId(@PathVariable("userId") String userId) {
        return userRepository.findById(UserId.of(userId))
                             .map(toUserDetails())
                             .map(List::of)
                             .orElseGet(Collections::emptyList);
    }

    private Function<User, UserDetails> toUserDetails() {
        return (user) -> new UserDetails(idOf(user), displayNameOf(user));
    }

    private String idOf(User user) {
        return user.getId().value();
    }

    private String displayNameOf(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }

}
