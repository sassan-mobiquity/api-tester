package com.mobiquity.assignment.steps;

import io.cucumber.java.en.Given;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import static com.mobiquity.assignment.RestClientBase.usersEndpoint;
import static io.restassured.RestAssured.given;

@Slf4j
@AllArgsConstructor
public class UserSteps {


    @Given("the username {string} corresponds to an existing user id")
    public void fetchUsername(String username) {
        log.info("fetching the user id for: " + username);
        String userId = given().
                queryParam("username", username).
                when().
                get(usersEndpoint).
                jsonPath().getString("id[0]");
        Assert.assertNotEquals(userId, 0, "username " + username + "does not exist!");
    }


}
