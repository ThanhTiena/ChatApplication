package main.Data;

import main.Models.Subjects.File;
import main.Models.Subjects.Group;
import main.Models.Reponsitories.Repository;
import main.Models.Subjects.User;

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

