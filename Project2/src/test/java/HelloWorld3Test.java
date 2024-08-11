import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Разные тесты
public class HelloWorld3Test {

    @Test
    public void testFor200(){
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/map")
                .andReturn();
        //Первый параметр - ожидаемое значение, Второй - фактичееский
        assertEquals(200,response.statusCode(),"Unexpected status code");
    }

    @Test
    public void testFor404(){
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/map2")
                .andReturn();
        //Первый параметр - ожидаемое значение, Второй - фактичееский
        assertEquals(404,response.statusCode(),"Unexpected status code");
    }
}