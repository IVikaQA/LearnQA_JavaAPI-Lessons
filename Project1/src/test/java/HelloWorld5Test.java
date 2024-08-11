import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HelloWorld5Test {

    @Test
    public void testRestAssured(){
        Map<String,String> params = new HashMap<>();
        params.put("name","John");
        JsonPath response = RestAssured
                //Строка given объяснияет Builder,что сейчас мы будем передавать парметр в запрос
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/hello")
                //Строка jsonPath() используется для получения объекта JsonPath из ответа API.
                .jsonPath();

       String name = response.get("answer");
       System.out.println(name);

        String name2 = response.get("answer2");
        if (name2 == null){
            System.out.println("The key 'answer2' is absent");
        } else {
            System.out.println(name2);
        }
    }
}