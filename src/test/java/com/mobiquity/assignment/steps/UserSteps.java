package com.mobiquity.assignment.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.mobiquity.assignment.RestClientBase.postsEndpoint;
import static com.mobiquity.assignment.RestClientBase.usersEndpoint;
import static io.restassured.RestAssured.given;

@Slf4j
@AllArgsConstructor
public class UserSteps {

    StepsState state;

    @Given("the username {string} corresponds to an existing user id")
    public void fetchUsername(String username) {
        log.info("fetching the user id for: " + username);
        state.userId = given().
                queryParam("username", username).
                when().
                get(usersEndpoint).
                jsonPath().getString("id[0]");
        Assert.assertNotEquals(state.userId, 0, "username " + username + "does not exist!");
    }

    @And("the user has created at least 1 post")
    public void fetchUserPosts() {
        log.info("fetching the posts for user id: " + state.userId);
        List<Integer> ids = given().
                queryParam("userId", state.userId).
                when().
                get(postsEndpoint).
                jsonPath().getList("id");
        state.postIds = (ArrayList<Integer>) ids;
        Assert.assertFalse(state.postIds.isEmpty(), "there are no posts created by this user");
    }

}
