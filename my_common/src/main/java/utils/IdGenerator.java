package utils;


import java.util.Random;

public class IdGenerator {

    public static String generatorId() {
        Random random = new Random();
        return String.valueOf(random.nextInt(9999999));
    }
}


