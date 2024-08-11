import com.fasterxml.jackson.annotation.JsonAlias;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Разнные тесты: Один позитивный(первый): Так как указан верный  URL и проверка прошла успешно;
//Второй тест - негативный: Так как проверка не проходит успешно
public class HelloWorld4Test {

    //1)Проверяем,что вернет тест,если не передаем имя пользоыателя
    @Test
    public void testHelloMethodWithoutName(){
        JsonPath response = RestAssured
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();
        String answer = response.getString("answer");
        assertEquals("Hello, someone",answer,"The answer is not expected");

    }

    //2)Проверяем,что вернет тест,если передаем имя пользователя, НО при проверке пишем
    //неверный ожидаемый результат
    @Test
    public void testHelloMethodWithName(){
        String name = "Username";
        JsonPath response = RestAssured
                .given()
                .queryParam("name",name)
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();
        String answer = response.getString("answer");
        assertEquals("Hello" + name,answer,"The answer is not expected");

    }

    //3)Проверяем,что вернет тест,если передаем имя пользователя
    @Test
    public void testHelloMethodWithName2(){
        String name = "Username";
        JsonPath response = RestAssured
                .given()
                .queryParam("name",name)
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();
        String answer = response.getString("answer");
        assertEquals("Hello, " + name,answer,"The answer is not expected");

    }
}
