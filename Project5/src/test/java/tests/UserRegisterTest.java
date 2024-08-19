package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.Assertions;
import lib.BaseTestCase;
import lib.DataGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class UserRegisterTest extends BaseTestCase {

    @Test
    public void testCreateUserWithExistingEmail(){
        String email = "vinkotov@example.com";

        /*
        Тест-1:Проверить метод "Create user"
        Нужно написать тест, который будет проверять на уже существующий в БД email при регистрации
        нового пользователя
         */
        Map<String,String> userData = new HashMap<>();
        userData.put("email","vinkotov@example.com");
        userData.put("password","123");
        userData.put("username","learnqa");
        userData.put("firstName","learnqa");
        userData.put("lastName","learnqa");

        Response responseCreateAuth = RestAssured
                .given()
                .body(userData)
                .post("https://playground.learnqa.ru/api/user")
                .andReturn();

        //Вариант 1
        //Смотрим текст ответа
        //System.out.println(responseCreateAuth.asString());
        //Смотрим код ответа сервера
        //System.out.println(responseCreateAuth.statusCode());

        //Вариант 2
        //Смотрим код ответа
        Assertions.assertResponseCodeEquals(responseCreateAuth,400);
        //Смотрим текст ответа сервера
        Assertions.assertResponseTextEquals(responseCreateAuth,"Users with email '" + email + "' already exists");
    }

    @Test
    public void testCreateUserSuccesfully(){
        //Генерим почту
        String email = DataGenerator.getRandomEmail();

        /*
        Тест-2:Проверить метод "Create user"
        Создаем нового пользователя. Ну почта уж точно уникальна
         */
        Map<String,String> userData = new HashMap<>();
        userData.put("email",email);
        userData.put("password","123");
        userData.put("username","learnqa");
        userData.put("firstName","learnqa");
        userData.put("lastName","learnqa");

        Response responseCreateAuth = RestAssured
                .given()
                .body(userData)
                .post("https://playground.learnqa.ru/api/user")
                .andReturn();

        //Смотрим,что код ответа сервера - 200
        Assertions.assertResponseCodeEquals(responseCreateAuth,200);
        //Смотрим ответ сервера
        //System.out.println(responseCreateAuth.asString());
        /*
        Смотрим ответ сервера: Проверяем наличие аргумента
         */
        Assertions.assertJsonHasKey(responseCreateAuth,"id");
    }
}
