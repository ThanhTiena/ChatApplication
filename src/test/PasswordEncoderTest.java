package test;

import main.Ulities.BryctEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderTest {
    private BryctEncoder passwordEncoder;
    private String password;
    private String hashPassword;

    @BeforeEach
    void setUp(){
        passwordEncoder = new BryctEncoder();
        password = "testPassword#@11";
        hashPassword = passwordEncoder.hashPassword(password);
    }

    @Test
    void hashPassword() {
        String expect = "94fjkwdf923ds";
        String actual = passwordEncoder.hashPassword(password);
        assertEquals(expect, actual);
    }

    @Test
    void checkPassword() {
        boolean actual = passwordEncoder.checkPassword(password,hashPassword);
        assertTrue(actual);
    }
}