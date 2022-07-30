package main.Application;

import main.Data.DataStorage;

import java.util.Scanner;

public class ChatMediator {
    private DataStorage dataStorage;
    private Scanner scanner = new Scanner(System.in);
    public ChatMediator(){
        dataStorage = DataStorage.getInstance();
    }
// Danh lam
    public void openConversation(){

    }
    private void getUserInterface(){
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
