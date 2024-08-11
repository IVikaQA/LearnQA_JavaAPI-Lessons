import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

//Простое сравнение: Здесь не очень удобно,так как результат не ИНФОРМАТИВЕН,
// так как используется assertTrue
public class HelloWorldTest {

    @Test
    public void testRestAssured(){
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/map2")
                .andReturn();
        //Идет сравнение значения response.getStatusCode() с 200
        //Если результат сравнения fasle, то выводим сообщение и false
        //Если результат сравнения true, то выводим просто true
        assertTrue(response.getStatusCode() == 200, "Unexpected status code");
    }
}
