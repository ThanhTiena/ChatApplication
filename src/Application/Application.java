package Application;

import Models.User;
import Ulities.PasswordEncoder;

public class Application {
    public static void main(String[] args) {

        PasswordEncoder passwordEncoder = new PasswordEncoder();
        System.out.println(passwordEncoder.hashPassword("Helllllllllllo"));
    }
}
