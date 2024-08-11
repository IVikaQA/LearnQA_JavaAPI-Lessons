import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class HelloWorld7Test {

    @Test
    public void testRestAssured(){
        Response response = RestAssured
                .given()
                //Передаеем строку в JSON-формате
                .body("{\"param1\":\"value1\",\"param2\":\"value2\"}")
                .post("https://playground.learnqa.ru/api/check_type")
                .andReturn();
        response.print();
    }
}