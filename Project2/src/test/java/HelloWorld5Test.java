import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Пример параметризованного теста
public class HelloWorld5Test {

    //Этот тег,говорит,что в тест будем передавать праметр и запустить столько раз,сколько
    //пар параметров у нас есть
    @ParameterizedTest
    //Мы указали один параметр, типа String, в скобках значения
    @ValueSource(strings = {"","John","Pete"})
    public void testHelloMethodWithoutName(String name){
        //Здесь параметры будут хранится
        Map<String,String> queryParams = new HashMap<>();

        //Если длина имени не равна нулю,
        //то мы добавим имя,входной параметр в метод testHelloMethodWithoutName, в HashMap
        if(name.length() > 0 ){
            queryParams.put("name",name);
        }

        JsonPath response = RestAssured
                .given()
                .queryParams(queryParams)
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();
        //Выводим из ответа значение переменной answer
        String answer = response.getString("answer");
        //Однострочный if - тернарный оператор
        //Ситаксис: (переменная) ? результат_если_true : результат_если_false
        //Дальше значение истинно или ложно мы добавляе в сравнение
        String expectedName = (name.length() > 0) ? name : "someone2";
        assertEquals("Hello, " + expectedName,answer,"The answer is not expected");

    }
}
