package main.Services;

import main.Data.DataStorage;
import main.Models.User;
import main.Ulities.UserException;

import java.util.function.Predicate;

public class UserService {

    private User user;
    private DataStorage dataStorage;
    public UserService(){
        this.dataStorage = DataStorage.getInstance();
    }
    public boolean Login(String username , String password) throws Exception {

        Predicate<User> predicate = user -> user.getFirstName().equals("") && user.getFullName().equals("");
        this.user = dataStorage.users.find(predicate); /* The return type is a USER */

        return this.user != null ? true : false;
    }

}
