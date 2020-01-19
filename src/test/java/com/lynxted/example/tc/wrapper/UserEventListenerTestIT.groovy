package com.lynxted.example.tc.wrapper

import com.lynxted.example.tc.KafkaContainerWrapperConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SpringBootTest(classes = [TestContainersWrapperApplication, KafkaContainerWrapperConfig], webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class UserEventListenerTestIT extends Specification {

    static final PollingConditions WAIT_FOR_PROCESSING = new PollingConditions(timeout: 3)

    @LocalServerPort
    int port
    @Autowired
    KafkaTemplate kafkaTemplate
    @Autowired
    MockMvc mockMvc

    def "registered user data should be available with REST"() {
        given: "there is User Data"
            String userId = randomAlphabetic(10)
            String firstName = randomAlphabetic(10)
            String lastName = randomAlphabetic(10)

        and: "User Registered event contain user data"
            UserRegisteredEvent userEvent = anUserRegisteredEvent()
                    .withUserId(userId).withFirstName(firstName).withLastName(lastName)

        when: "User Registered event was published into kafka"
            published(userEvent)

        then: "User Details are available through REST API"
            WAIT_FOR_PROCESSING.eventually {
                userDetailsIsRequestedFor(userId).andExpect(jsonPath('$[0].displayName').value(firstName + " " + lastName))
            }
    }

    UserRegisteredEvent anUserRegisteredEvent() {
        new UserRegisteredEvent()
    }

    void published(UserRegisteredEvent userRegisteredEvent) {
        kafkaTemplate.send("user-data", userRegisteredEvent.userId, userRegisteredEvent.eventBody()).get()
    }

    ResultActions userDetailsIsRequestedFor(String userId) {
        mockMvc.perform(get("/api/users/$userId").accept(APPLICATION_JSON))
    }
}
