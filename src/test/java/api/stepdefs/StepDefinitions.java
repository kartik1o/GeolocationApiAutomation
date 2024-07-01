package api.stepdefs;

import api.endpoints.Routes;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class StepDefinitions {

    private final Routes config = new Routes();
    RequestSpecification request;
    Response response;
    @Given("I have geolocation API with baseUrl {string}")
    public void i_have_geolocation_api_with_base_url(String baseUrl) {
        request = given().baseUri(baseUrl);
    }
    @Given("API key is included in queryParam and contentType is {string}")
    public void api_key_is_included_in_query_param_and_content_type_is(String contentType) {
        request.queryParam("key", config.getApiKey()).contentType(contentType);
    }
    @When("I post request to geolocation API with valid request body")
    public void i_post_request_to_geolocation_api_with_valid_request_body() {
        response = request.post("geolocation/v1/geolocate");
    }
    @Then("I get successful response from API")
    public void i_get_successful_response_from_api() {
        response.then().log().all();
        assertEquals(response.getStatusCode(), 200);
        assertNotNull(response.getBody().jsonPath().get("accuracy"));
        assertNotNull(response.getBody().jsonPath().get("location"));
        assertNotNull(response.getBody().jsonPath().get("location.lat"));
        assertNotNull(response.getBody().jsonPath().get("location.lng"));
    }
}
