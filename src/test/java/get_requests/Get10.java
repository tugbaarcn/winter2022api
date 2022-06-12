package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get10 extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/13
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            {
                “meta”: null,
                “data”: {
                    “id”: 13,
                    “name”: “Fr. Ajit Prajapat”,
                    “email”: “ajit_fr_prajapat@barrows.org”,
                    “gender”: “female”,
                    “status”: “active”
                }
            }
     */

    @Test
    public void get01(){
        //1.Step: Set the expected Data
        spec.pathParams("first","users","second",13);

        //2.Step: Set the expected data
        Map<String,String> dataKeyMap = new HashMap<>();
        dataKeyMap.put("name","Rupinder Shukla");
        dataKeyMap.put("email","ajit_fr_prajapat@barrows.org");
        dataKeyMap.put("gender","female");
        dataKeyMap.put("status","active");

        Map<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("meta",null);
        expectedDataMap.put("data",dataKeyMap);

        //3.Step: Send the request and get the response
       Response response = given().spec(spec).when().get("/{first}/{second}");

       Map<String, Object> actualDataMap = response.as(HashMap.class);

       //4.Step: Do Assertions
        assertEquals(expectedDataMap.get("meta"), actualDataMap.get("meta"));
        assertEquals(dataKeyMap.get("name"), ((Map)actualDataMap.get("data")).get("name"));



    }

}
