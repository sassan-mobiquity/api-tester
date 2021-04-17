package com.mobiquity.assignment.steps;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.mobiquity.assignment.RestClientBase.commentsEndpoint;
import static com.mobiquity.assignment.RestClientBase.postsEndpoint;
import static io.restassured.RestAssured.when;

@Slf4j
@AllArgsConstructor
public class PostSteps {

    StepsState state;

    @When("all the comments associated with the user's posts are fetch from the API")
    public void fetchPostsComments() {
        state.emails = (ArrayList<String>) state.postIds.stream().
                parallel().
                flatMap(postId ->
                {
                    log.info("fetching the comments for post id: " + postId);
                    List<HashMap<String, String>> comments = when().
                            get(postsEndpoint + postId + commentsEndpoint).
                            jsonPath().getList("");
                    return comments.stream().
                            distinct().
                            map(comment -> comment.get("email"));
                }).
                collect(Collectors.toList());

        Assert.assertFalse(state.emails.isEmpty(), "no emails found in the comments");
    }

    @Then("all the email addresses in the comments have proper format")
    public void validateEmails() {
        Pattern emailRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        state.emails.removeIf(email -> emailRegex.matcher(email).find());
        Assert.assertEquals(state.emails.size(), 0,
                "The following email addresses did not have proper format: \n" + state.emails + "\n");
    }


}
