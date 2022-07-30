package main.Data;

import main.Models.Group;
import main.Models.User;

import javax.swing.text.html.parser.Entity;
import java.util.*;

public class DataStorage {
    private Map<String, User> users = new HashMap<String, User>();
    private static DataStorage instance = new DataStorage();

    private DataStorage() {
    }

    public static DataStorage getInstance() {
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);

    }

    public int getTotalUser() {
        for (String id : users.keySet()) {
            System.out.println(id + " " + users.get(id).getFullName());
        }
        return users.size();
}

