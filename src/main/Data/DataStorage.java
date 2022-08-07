package main.Data;

import main.Models.Subjects.*;
import main.Models.Reponsitories.Repository;

public class DataStorage {

    public Repository<User> users;
    public Repository<Group> groups;
    public Repository<File> files;
    public Repository<Message> messages;
    public Repository<Alias> alias;
    private static DataStorage instance = new DataStorage();

    private DataStorage() {
        users = new Repository<User>();
        groups = new Repository<Group>();
        files = new Repository<File>();
        messages = new Repository<Message>();
        alias = new Repository<Alias>();
    }

    public static DataStorage getInstance() {
        return instance != null ? instance : new DataStorage();
    }
}

