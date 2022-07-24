package main.Data;

import main.Models.Group;

import java.util.HashMap;
import java.util.Map;

public class DataStorage {
    private Map<String,Object> data = new HashMap<>(){
        {
            put("GROUPS", new HashMap<String, Group >());
        }
    };
    private static DataStorage instance = new DataStorage();

    private DataStorage() {
    }

    public static DataStorage getInstance(){
        return instance;
    }
}

