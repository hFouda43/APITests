package org.petstore.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.petstore.pojo.AddPet;
import org.petstore.resources.EndPoints;
import org.petstore.resources.TestDataBuild;
import org.petstore.resources.Utils;
import org.testng.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils {

    //Creating an object of test data class to load the payloads
    TestDataBuild data = new TestDataBuild();
    //Defining the requests and responses
    RequestSpecification addPetRequest;
    RequestSpecification getPetRequest;
    RequestSpecification updatePetUsingFormDataRequest;
    RequestSpecification deletePetRequest;
    Response response;

    //Defining the global response objects used by all tests
    static AddPet addPetResponseObject;
    AddPet getPetResponseObject;

    //Step definitions implementation

    //add pet request preparation
    @Given("Add Pet Payload")
    public void add_pet_payload() {

        try {
            addPetRequest = given().spec(requestSpecification()).body(data.addPetPayload());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Send AddPet Request
    @When("user calls AddPet API with post http request")
    public void user_calls_api_with_post_http_request() {
        response = addPetRequest.when().post(EndPoints.getPetEndPoint())
                .then().extract().response();
    }

    //Validating any successful request with status code 200
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer statusCode) {
        Assert.assertEquals(response.statusCode(), statusCode);

    }

    //AddPet API assertion
    @And("verify that the pet name is correctly added")
    public void verifyThatThePetNameIsCorrectlyAdded() {
        addPetResponseObject = response.as(AddPet.class);
        Assert.assertEquals(addPetResponseObject.getName(), data.addPetPayload().getName());
    }

    //Send GetPet Request
    @When("user calls getPet API with get http request")
    public void userCallsGetPetAPIWithGetHttpRequest() {
        try {
            getPetRequest = given().spec(requestSpecification()).pathParam("pet_Id", addPetResponseObject.getId());
            response = getPetRequest.when().get(EndPoints.getPetEndPoint() + "/{pet_Id}").then().extract().response();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //GetPet API assertion
    @And("Verify that the user can get the added pet using the provided id")
    public void verifyThatTheUserCanGetTheAddedPetUsingTheProvidedId() {
        getPetResponseObject = response.as(AddPet.class);
        Assert.assertEquals(getPetResponseObject.getId(), addPetResponseObject.getId());
    }

    //Send UpdatePet request using form params
    @When("user Calls UpdatePet API with post http request with {string} and {string}")
    public void userCallsUpdatePetAPIWithPostHttpRequestWithAnd(String name, String petStatus) {
        try {
            updatePetUsingFormDataRequest = given().spec(requestSpecification()).
                    pathParam("pet_Id", addPetResponseObject.getId()).
                    contentType("application/x-www-form-urlencoded").
                    formParam("name", name).formParam("status", petStatus);
            response = updatePetUsingFormDataRequest.when().post(EndPoints.getPetEndPoint() + "/{pet_Id}").
                    then().extract().response();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Validating any response returning status code and message
    @And("the returned message displays code {int} and the pet id")
    public void theReturnedMessageDisplaysCodeAndThePetId(int statusCode) {
        String petResponse = response.asString();
        JsonPath jsonPath = new JsonPath(petResponse);
        Assert.assertEquals(jsonPath.getInt("code"), statusCode);
        Assert.assertEquals(jsonPath.get("message"), String.valueOf(addPetResponseObject.getId()));
    }
//Validating that the pet has been updated
    @And("the name is updated with {string} and status with {string} correctly")
    public void theNameIsUpdatedWithAndStatusWithCorrectly(String updatedPetName, String petStatus) {
        getPetResponseObject = response.as(AddPet.class);
        Assert.assertEquals(getPetResponseObject.getName(), updatedPetName);
        Assert.assertEquals(getPetResponseObject.getStatus(), petStatus);
    }

//DeletePet API Request
    @When("user Calls DeletePet API with delete http request")
    public void userCallsDeletePetAPIWithDeleteHttpRequest() {
        try {
            deletePetRequest = given().spec(requestSpecification()).header("api_key", "special-key")
                    .pathParam("pet_Id", addPetResponseObject.getId());
            response = deletePetRequest.when().delete(EndPoints.getPetEndPoint() + "/{pet_Id}").then().extract().response();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//Validating any request failing with failure status code
    @Then("the API call fails with status code {int}")
    public void theAPICallFailsWithStatusCode(int statusCode) {
        Assert.assertEquals(response.statusCode(), statusCode);

    }
}
