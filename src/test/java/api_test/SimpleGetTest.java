package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SimpleGetTest extends BaseAPIClass{
    @Test
    public void getAllUsers(){
        //Specify  the base URL or Endpoint of the Rest API
        RestAssured.baseURI="https://reqres.in/api/users/";

        //Create  an object from RequestSpecification Class in order to send the request to the server
        //Added the server URL to make the request
        RequestSpecification httpRequest = RestAssured.given();

        //make a request to the server and this will return the response
        Response response = httpRequest.request(Method.GET);
        LOGGER.debug(response.getBody().asString());

        //Assert the correct statues is returned
        Assert.assertEquals(response.getStatusCode(),200);

        //Get the jsonpath object instance from the response.
        JsonPath jsonPath = response.jsonPath();
        List<String> list = jsonPath.get("data.email");

        //validate that a specific user email does exist in the response body
        String emailId = "george.bluth@reqres.in";
        boolean emailExist = list.contains(emailId);
        Assert.assertTrue(emailExist,emailId+"does not exist");
    }
}
