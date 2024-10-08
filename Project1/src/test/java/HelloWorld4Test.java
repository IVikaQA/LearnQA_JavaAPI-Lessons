import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HelloWorld4Test {

    @Test
    public void testRestAssured(){
        Map<String,String> params = new HashMap<>();
        params.put("name","John");
        Response response = RestAssured
                //Строка given объяснияет Buuilder,что сейчас мы будем передавать парметр в запрос
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/hello")
                .andReturn();
        response.prettyPrint();
    }
}