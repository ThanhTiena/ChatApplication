package main.Application;

import main.Models.File;
import main.Models.Group;
import main.Models.User;
import main.Services.UserService;
import main.Ulities.PasswordEncoder;
import main.Ulities.UserException;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        String hashPassword = passwordEncoder.hashPassword("Helllllllllllo");

//        Map<String, Object> data = new HashMap<String, Object>();
//
//        data.put("groups", new HashMap<String, Group>());
//        data.put("files", new HashMap<String, File>());
        User user = new User();
        user.setFirstName("tien");
        user.setHashPassword(passwordEncoder.hashPassword("123"));

        try {
            UserService userService = new UserService(user);
            userService.Login("asdasd","asdfasdfa");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
