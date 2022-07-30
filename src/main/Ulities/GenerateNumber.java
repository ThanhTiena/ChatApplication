package main.Ulities;

import java.util.Random;

public class GenerateNumber {

    public GenerateNumber() {
    }

    public static String generateUserId() {
        return "USER-" + new Random().nextInt(1000);
    }

}
