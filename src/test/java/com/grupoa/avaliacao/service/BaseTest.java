package com.grupoa.avaliacao.service;

import com.grupoa.avaliacao.model.Student;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.Random;

@SpringBootTest
public class BaseTest {

    protected static final String OVERALL_CATEGORY = "Overall";
    protected static final Integer OVERALL_ID = 1;

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;

    private static SecureRandom random = new SecureRandom();

    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);

        }

        return sb.toString();

    }

    public static Integer getRandomIntegerBetweenRange(Integer min, Integer max){
        Integer x = (int) ((Math.random()*((max-min)+1))+min);
        return x;
    }

    public static Long getRandomLong() {
        return new Random().nextLong();
    }

    public static Student generateRandomStudent() {
        return Student.builder()
                .cpf(getRandomLong())
                .email(generateRandomString(5)  + "@" + generateRandomString(5))
                .name(generateRandomString(10))
                .academicRegister(getRandomLong()).build();
    }

}
