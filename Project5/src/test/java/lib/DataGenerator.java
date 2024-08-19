package lib;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataGenerator {

    //Генератор названия почты
    public static String getRandomEmail(){
        /*
        Определяем строку,которую хотим добавить при генерации названия почты
        Делаем с помощью класса SimpleDateFormat
         */
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        return "learnqa"+ timestamp + "@example.com";
    }

    /*
    Метод возвращает дефолтными значения, Но каждый раз с
    новой почтой. Нужно для создания нового пользователя.
     */
    public static Map<String,String> getRegistrationData(){
        Map<String,String> data = new HashMap<>();
        data.put("email",DataGenerator.getRandomEmail());
        data.put("password","123");
        data.put("user","learnqa");
        data.put("firstName","learnqa");
        data.put("lastName","learnqa");

        return data;
    }

    /*
        Такой же как и метод выше,Но есть отличие
        
     */
    public static Map<String,String> getRegistrationData(Map<String,String> nonDefaultValues){
        Map<String,String> defaultValues = DataGenerator.getRegistrationData();

        Map<String,String> userData = new HashMap<>();
        String[] keys = {"email","password","username","firstName","lastName"};
        for(String key:keys){
            if (nonDefaultValues.containsKey(key)) {
                userData.put(key,nonDefaultValues.get(key));
            } else {
                userData.put(key,defaultValues.get(key));
            }
        }
        return userData;
    }
}
