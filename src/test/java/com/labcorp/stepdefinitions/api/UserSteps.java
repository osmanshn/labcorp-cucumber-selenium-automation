package com.labcorp.stepdefinitions.api;

import static io.restassured.RestAssured.*;

import com.labcorp.models.User;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class UserSteps {

    private Response response;
    private User user;

    @Before("@api_testing")
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }


    @Given("I send a POST request to create a user with the following details {string} {string} {string} {string} {string} {string} {string} {string}")
    public void createUser(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {
        long userId = Long.parseLong(id);
        int status = Integer.parseInt(userStatus);
        List<User> payload = new ArrayList<>();

        user = new User(userId, username, firstName, lastName, email, password, phone, status);
        payload.add(user);

        response = given()
                .contentType("application/json")
                .body(payload)
                .post("/user/createWithArray");

        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("the user should be created successfully")
    public void retrieveUserDetails() {
        response = RestAssured.get("/user/" + user.getUsername());
        Assert.assertEquals(200, response.getStatusCode());

        // Convert JSON response to User object using User class
        user = response.as(User.class);

        // Validate user details
        Assert.assertEquals("RRRR", user.getFirstName()); // Check first name
        Assert.assertEquals("LLL", user.getLastName()); // Check last name
        Assert.assertEquals("we@gmail.com", user.getEmail()); // Check email
        Assert.assertEquals("23dwewe", user.getPassword()); // Check password
        Assert.assertEquals("2324433", user.getPhone()); // Check phone number
        Assert.assertEquals(0, user.getUserStatus()); // Check user status
        Assert.assertEquals(43433, user.getId()); // Check ID
        Assert.assertEquals("323243431qw", user.getUsername()); // Check Username
    }

    @When("I attempt to retrieve user details with the non-existing username {string}")
    public void retrieveUserDetailsWithNonExistingUsername(String nonExistingUsername) {
        response = RestAssured.get("/user/" + nonExistingUsername);
    }

    @Then("the API should respond with a {string}")
    public void verifyStatusCode(String statusCode) {
        response.then().statusCode(Integer.parseInt(statusCode));
    }

    @When("I update the user {string}")
    public void updateUser(String firstName) {
        user.setFirstName(firstName);
        response = given()
                .contentType("application/json")
                .body(user)
                .put("/user/" + user.getUsername());
    }

    @Then("the API should respond with a status code indicating successful update")
    public void verifyResponseCode() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("I attempt to delete the user with the username {string}")
    public void deleteExistingUser(String existingUsername) {
        response = RestAssured.given()
                .delete("/user/" + existingUsername);
    }

}
