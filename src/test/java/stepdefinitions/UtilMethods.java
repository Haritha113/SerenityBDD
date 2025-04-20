package stepdefinitions;

import java.lang.invoke.StringConcatFactory;
import java.util.Random;
import java.util.UUID;

public class UtilMethods {

    public static String generateRandomUsername() {
        return "user_" + UUID.randomUUID().toString().substring(0, 6);
    }

    public static String generateRandomPassword() {
        return "Pwd@" + new Random().nextInt(9999);
    }

    public static String generateRandomEmpName() {
        return  String.valueOf((char)(new Random().nextInt(26) + 'a'));
    }
}
