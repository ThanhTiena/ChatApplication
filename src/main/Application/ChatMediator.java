package main.Application;

import main.Data.DataStorage;

import java.util.Scanner;

public class ChatMediator {
    private DataStorage dataStorage;

    public ChatMediator(){
        dataStorage = DataStorage.getInstance();
    }
// Danh lam
    public void openConversation(){
        Scanner scanner = new Scanner(System.in);
    }

    private String getUserInterface(){
        String userInterface;
        userInterface = "########################";
        userInterface += "1. Login";
        userInterface += "2. Logout";
        userInterface += "3. Open Chat";
        userInterface += "#######################";
        return userInterface;
    }
}
