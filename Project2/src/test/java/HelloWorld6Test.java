import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//НЕ РАБОТАЕТ ВЕРНО, НО суть ясна

//Рассматриваем теги: BeforeEach
//Эти теги вызываются до и после каждого теста в классе
//BeforeEach - В этой функции функции прописывается вся логика,
//с которой должен начинаться каждый тест в классе. Например,
//подготовка тестовых данных
public class HelloWorld6Test {
    //1)Список параметров, которые мы будем использовать в тестах
    String cookie;
    String header;
    int userIdOnAuth;

    //2)Делаем первый запрос и кладем в переменные те значения, которые мы будем использовать
    //в тестах
    @BeforeEach
    public void loginUser(){
        Map<String,String> authData = new HashMap<>();
        authData.put("email","vinkotov@example.com");
        authData.put("password","1234");

        Response responseGetAuth = RestAssured
                .given()
                .body(authData)
                .post("https://playground.learnqa.ru/api/user/login")
                .andReturn();
        //this - Спец.указатель,который позволяет переменную сделать полем класс
        //и передавать ее значения из одной функции в другие
        //Указывать this нужно если у нас внутри функции две переменных с одинаковым именем
        this.cookie = responseGetAuth.getCookie("auth_sid");
        this.header = responseGetAuth.getHeader("x-csrf-token");
        this.userIdOnAuth = responseGetAuth.jsonPath().getInt("user_id");
    }

    //3)В тесте уже делаем второй запрос и в зависимости от проверки используем
    //ту или иную переменную класса
    @Test
    public void testAuthUser(){
        JsonPath responseCheckAuth = RestAssured
                .given()
                .header("x-csrf-token", this.header)
                .cookie("auth_sid",this.cookie)
                .get("https://playground.learnqa.ru/api/user/auth")
                .jsonPath();

        int userIdOnCheck = responseCheckAuth.getInt("user_id");
        assertTrue(userIdOnCheck > 0, "Unexpected user id " + userIdOnCheck);

        assertEquals(
                userIdOnAuth,
                userIdOnCheck,
                "user id from auth request is not equal to user_id from check request"
        );
    }

    @ParameterizedTest
    @ValueSource(strings ={"cookie","header"})
    public void testNegativeAuthUser(String condition){
        RequestSpecification spec = RestAssured.given();
        spec.baseUri("https://playground.learnqa.ru/api/user/auth");

        if (condition.equals("cookie")) {
            spec.cookie("auth_sid", this.cookie);
        } else if (condition.equals("headers")){
                spec.header("x-csrf-token",this.header);
        } else {
                throw new IllegalArgumentException("Condition value is known: " + condition);
                }
        //Получить ответ и взять из него json
        JsonPath responseForCheck = spec.get().jsonPath();
        assertEquals(0,responseForCheck.getInt("user_id"),"user_id should be 0 for unauth request");
    }
}

