package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post02 extends JsonPlaceHolderBaseUrl {
     /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
        Note 1: The data you send in the request is called "Request Body" or "Payload"
        Note 2 : The data you get in the response is called "Response Body"
     */
    @Test
    public void post01(){

        //1.Set the URL
        spec.pathParam("first","todos");

        //2.Step: Set the expected data ( Request Body or Payload)
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String,Object> expectedDataMap =  expectedData.expectedDataWithAllKeys(55,"Tidy your room",false);

        //3.Step: Send the request and get the response
       Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
       response.prettyPrint();

       //4.Step: Do Assertions
        response.then().assertThat().statusCode(201);

        Map<String,Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));


    }




















}
