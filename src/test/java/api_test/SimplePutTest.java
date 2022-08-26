package api_test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimplePutTest {
        private static final Logger LOGGER = LogManager.getLogger(SimplePutTest.class);

        @Test
        public void UpdateNewUser() {
            LOGGER.info("----------API Test: Update existing user------------");
            RestAssured.baseURI= "https://reqres.in/api/users/";

            RequestSpecification httpRequest = RestAssured.given();

            Faker faker = new Faker();
            String name = faker.name().fullName();
            String job = faker.job().title();
            LOGGER.debug("Name is :" + name);
            LOGGER.debug("Job is:" + job);

            JSONObject reqBody = new JSONObject();
            reqBody.put("name", name);
            reqBody.put("job", job);

            httpRequest.header("Content-type", "application/json");
            httpRequest.body(reqBody.toJSONString());

            String id = "2";
            Response response = httpRequest.request(Method.PUT, id);
            LOGGER.debug(response.getBody().asString());

            Assert.assertEquals(response.getStatusCode(),200);

            JsonPath jsonPath = response.jsonPath();
            String actualName = jsonPath.getString("name");

            Assert.assertEquals(actualName, name);

            LOGGER.info("---------End Test: Update existing user---------");
        }
    }


