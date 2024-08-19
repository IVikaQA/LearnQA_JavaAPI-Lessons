package lib;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Assertions {

    //Значения в json доступны по определенному имени
    //и равняется тому,что мы ожидали
    /*
    На вход эта функция будет получать объект с ответом сервера,чтобы вытянуть из него текст
    также имя по котрому нужно искать значение и ожидаемый результат
    На выходе функция assertTrue будет сравнивать ожидаемый результат(expectedValue)
    с value, котрое получено из JSON ответа
    Если правильное,то выдается true, Если результат сравнения - false,
    то выдается сообщение
     */

    /*
    На вход метод получает объект с ответом сервера, имя которое нужно найти в ответе,ожидаемое значение
     */
    public static void asserJsonByName(Response Response,String name, int expectedValue){
        //1) Получаем объект с ответом сервера
        Response.then().assertThat().body("$",hasKey(name));
        //2)Получаем значение нужного параметра,передав его имя
        int value = Response.jsonPath().getInt(name);
        //3) Сравнием ожидаемое значение со значением из ответа сервера,если не совпадает
        //то выводим сообщение
        assertEquals(expectedValue,value,"JSON value is not equal to expected value");
    }
}
