import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class HelloWorld3Test {

    @Test
    public void testRestAssured(){
        Response response = RestAssured
                //Строка given объяснияет Buuilder,что сейчас мы будем передавать парметр в запрос
                .given()
                .queryParam("name","Jhon")
                .get("https://playground.learnqa.ru/api/hello")
                .andReturn();
        response.prettyPrint();
    }
}
