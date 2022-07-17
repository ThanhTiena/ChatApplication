package main.Ulities;

import org.mindrot.BCrypt;

public class PasswordEncoder {

    public String hashPassword(String plainTextPassword) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(plainTextPassword, salt);
    }

    public boolean checkPassword(String plainTextPassword, String hashedPassword) {
        if (null == hashedPassword || !hashedPassword.startsWith("$2a$")) {
            throw new RuntimeException("Password is invalid");
        }
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
