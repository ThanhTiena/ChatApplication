package main.Application;

import main.Data.DataStorage;
import main.Services.GroupService;
import main.Services.MessageService;
import main.Services.UserService;

import java.util.Scanner;

public class ChatMediator {
    private DataStorage dataStorage;
    private Scanner scanner = new Scanner(System.in);
    private GroupService groupService;
    private UserService userService;
    private MessageService messageService;

    public ChatMediator() {
        dataStorage = DataStorage.getInstance();
    }

    public void openConversation() {
        groupService = new GroupService();
        userService = new UserService();
        messageService = new MessageService();
    }

    private void getUserInterface() {
        String userInterface;
        userInterface = "######################## \n";
        userInterface += "1. Login \n";
        userInterface += "2. Logout \n";
        userInterface += "3. Open Chat \n";
        userInterface += "####################### \n";
        userInterface += "Your choice: ";
        System.out.println(userInterface);
    }

    public void doTask() {
        int choice;
        do {
            getUserInterface();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    doLogin();
                    break;
                case 2:
                    doLogout();
                    break;
                case 3:
                    doOpenChat();
                    break;
                default:
                    System.out.println("Wrong input");
            }
        } while (choice != 3);
    }

    private void doLogin() {
        System.out.println("Login is loading");
    }

    private void doLogout() {
        System.out.println("Logout is loading");
    }

    private void doOpenChat() {
        System.out.println("OpenChat is loading");
    }
}
