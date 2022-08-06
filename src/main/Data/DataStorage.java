package main.Data;

import main.Models.Subjects.File;
import main.Models.Subjects.Group;
import main.Models.Reponsitories.Repository;
import main.Models.Subjects.Message;
import main.Models.Subjects.User;

public class DataStorage {

    public Repository<User> users;
    public Repository<Group> groups;
    public Repository<File> files;
    public Repository<Message> messages;
    private static DataStorage instance = new DataStorage();

    private DataStorage() {
        users = new Repository<User>();
        groups = new Repository<Group>();
        files = new Repository<File>();
        messages = new Repository<Message>();
    }

    public static DataStorage getInstance() {
        return instance != null? instance : new DataStorage();
    }
}

