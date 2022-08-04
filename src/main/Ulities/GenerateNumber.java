package main.Ulities;

import java.util.Arrays;
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

    /* Group Generated Number Activities */
    public static String generateGroupId() {
        return "GROUP-" + new Random().nextInt(1000);
    }

    public static String generateGroupCode() {
        String accessCode = "";
        int[] code = new Random().ints(10_000_000, 100_000_000)
                .distinct()
                .limit(1)
                .toArray();
        return String.valueOf(code[0]);
    }

    public static String generateFileId() {
        return "FILE-" + new Random().nextInt(1000);
    }
}
