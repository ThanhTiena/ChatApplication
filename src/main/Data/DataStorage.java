package main.Data;

import main.Models.File;
import main.Models.Group;
import main.Models.Reponsitories.Repository;
import main.Models.User;

import javax.swing.text.html.parser.Entity;
import java.util.*;

public class DataStorage {

    public Repository<User> users;
    public Repository<Group> groups;
    public Repository<File> files;
    private static DataStorage instance = new DataStorage();

    private DataStorage() {
        users = new Repository<>();
        groups = new Repository<>();
        files = new Repository<>();
    }

    public static DataStorage getInstance() {
        return instance;
    }
}

