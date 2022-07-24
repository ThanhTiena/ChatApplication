package main.Data;

import main.Models.Group;
import main.Models.User;

import javax.swing.text.html.parser.Entity;
import java.util.*;

public class DataStorage {
    private Map<String,Object> data = new HashMap<>(){
        {
            put("GROUPS", new HashMap<String, Group >());
            put("USER", new HashMap<String, User>());
        }
    };
    private List<Entity> dataList = new ArrayList<>();
    private static DataStorage instance = new DataStorage();

    private DataStorage() {
    }

    public static DataStorage getInstance(){
        return instance;
    }

    public List<User> find(){
        return null;
    }
    public Entity get(){
        return null;
    }
}

