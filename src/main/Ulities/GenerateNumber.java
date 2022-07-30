package main.Ulities;

import java.util.Random;

public class GenerateNumber {

    public GenerateNumber() {
    }

    public static String generateUserId() {
        return "USER-" + new Random().nextInt(1000);
    }
    public static String generateMessageId() {
        return "MESS-" + new Random().nextInt(1000);
    }

    public static String generateGroupId() {
        return "GROUP-" + new Random().nextInt(1000);
    }
    public static String generateFileId() {
        return "FILE-" + new Random().nextInt(1000);
    }
}
