package main.Application;

import main.Data.DataStorage;
import main.Models.Enums.Gender;
import main.Models.Enums.GroupType;
import main.Models.Subjects.File;
import main.Models.Subjects.Group;
import main.Models.Subjects.User;
import main.Services.GroupService;
import main.Services.UserService;
import main.Ulities.BryctEncoder;
import main.Ulities.GenerateNumber;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.function.Predicate;

public class Application {
    public static void main(String[] args) {
//        ChatMediator chatMediator = new ChatMediator();
//        chatMediator.doTask();
        try {
            UserService userService = new UserService();
            userService.addNewUser("danh","123456","Danh","Dang", Gender.MALE, new Date());
            userService.addNewUser("tien","123456","Tien","Nguyen", Gender.MALE, new Date());
            userService.addNewUser("nhan","123456","Nhan","Nguyen", Gender.MALE, new Date());

            DataStorage dataStorage = DataStorage.getInstance();
            dataStorage.users.findAll().stream().toList().forEach(user -> {
                System.out.println(user.getFullName());
            });
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
