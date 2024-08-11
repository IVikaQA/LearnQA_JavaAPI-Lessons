import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HelloWorld19Test {

    @Test
    public void testRestAssured(){
        Map<String,String> data = new HashMap<>();
        data.put("login","secret_login");
        data.put("password","secret_pass");

        //1)Шлем пеервый запрос
        Response responseForGet = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();

        //2)Кладем куки в переменную
        String responseCookie = responseForGet.getCookie("auth_cookie");

        //3)Ложим сохраненную куки в Map
        Map<String,String> cookies = new HashMap<>();
        cookies.put("auth_cookie",responseCookie);

        Response responseForCheck = RestAssured
                .given()
                .body(data)
                //4)Передаем куки во втором запросе
                .cookies(cookies)
                .when()
                //5)Шлем второй запрос, передав в него куки
                .post("https://playground.learnqa.ru/api/check_auth_cookie")
                .andReturn();

        responseForCheck.print();
    }
}