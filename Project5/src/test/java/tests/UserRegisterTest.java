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

    /*
    Тест-2:Проверять метод "Create user"
    Но здесь в отличии от первого варианта,почта генерится каждый раз новая и поэтому
    пользователь создается
     */
    @Test
    public void testCreateUserSuccesfully(){
        //Генерим почту
        String email = DataGenerator.getRandomEmail();

        /*
        Создаем нового пользователя, почта уникальна.
        Так как генерится с помощью метода getRandomEmail() класса DataGenerator
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

    /*
    Тест-3:Проверить метод "Create user" для существующего пользователя
    Только здесь данные берутся из метода getRandomEmail(),getRegistrationData()
     */
    @Test
    public void testCreateUserWithExistingEmail2(){
        //String email = "vinkotov@example.com";
        //Генерим почту
        String email = DataGenerator.getRandomEmail();

        Map<String,String> userData = new HashMap<>();
        userData.put("email",email);
        //Все остальные данные для user берутся из метода getRegistrationData()
        userData = DataGenerator.getRegistrationData(userData);
    }

    /*
    Тест-4:Проверять метод "Create user" для нового пользователя
    Но здесь в отличии от первого варианта,почта генерится каждый раз новая и поэтому
    пользователь создается
     */
    @Test
    public void testCreateUserSuccesfully2(){
        //Генерим почту
        String email = DataGenerator.getRandomEmail();

        Map<String,String> userData = DataGenerator.getRegistrationData();

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
