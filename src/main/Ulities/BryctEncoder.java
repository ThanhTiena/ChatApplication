package main.Ulities;

import org.mindrot.BCrypt;

import java.util.function.Predicate;

public class BryctEncoder {

    public static String hashPassword(String plainTextPassword) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(plainTextPassword, salt);
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        if (null == hashedPassword || !hashedPassword.startsWith("$2a$")) {
            throw new RuntimeException("Password is invalid");
        }
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public static String hashNumber(Integer number) {
        return BCrypt.hashpw(String.valueOf(number), BCrypt.gensalt());
    }
}
