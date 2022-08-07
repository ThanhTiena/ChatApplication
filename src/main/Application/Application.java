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

        try {
            ChatMediator chatMediator = new ChatMediator();
            chatMediator.openConversation();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
