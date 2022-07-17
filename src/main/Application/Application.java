package main.Application;

import main.Ulities.PasswordEncoder;

public class Application {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        String hashPassword = passwordEncoder.hashPassword("Helllllllllllo");
    }
}
