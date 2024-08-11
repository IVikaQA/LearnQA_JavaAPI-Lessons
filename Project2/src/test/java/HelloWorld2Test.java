import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Тест имеет информативный ответ, так как используется assertEquals
public class HelloWorld2Test {

    @Test
    public void testRestAssured(){
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/map2")
                .andReturn();
        //Первый параметр - ожидаемое значение, Второй - фактичееский
        assertEquals(200,response.statusCode(),"Unexpected status code");
    }
}