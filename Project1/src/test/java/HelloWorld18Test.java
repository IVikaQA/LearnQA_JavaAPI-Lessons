import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HelloWorld18Test {

    @Test
    public void testRestAssured(){
        Map<String,String> data = new HashMap<>();
        data.put("login","secret_login");
        data.put("password","secret_passw");

        Response response = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();

        //Смотрим auth_cookie в случае неверного пароля
        String responseCookies = response.getCookie("auth_cookie");
        System.out.println(responseCookies);

        //Выводим текст ответа
        //\n - после вывода строки мы переносим курсор на новую строку
        System.out.println("\nPretty text:");
        response.prettyPrint();

        //Выводим заголовки
        System.out.println("\nHeaders:");
        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);

        //Выводим куки
        System.out.println("\nCookies:");
        Map<String,String> response2Cookies = response.getCookies();
        System.out.println(response2Cookies);
    }
}