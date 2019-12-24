package utils;


import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class IdGenerator {

    public static String generatorId() {
        //生成6位随机数
        return RandomStringUtils.randomNumeric(6);
    }
}


