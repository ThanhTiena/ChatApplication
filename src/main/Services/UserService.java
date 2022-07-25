package main.Services;

import main.Models.User;
import main.Ulities.UserException;

public class UserService {
    private User user;

    public UserService(User user){
        this.user = user;
    }
    public boolean Login(String username , String password) throws Exception {

        if(!username.equals(user.getFirstName())){
            throw new Exception("Username invalid");
        }
        if(!password.equals(user.getHashPassword())){
            throw new Exception("Password invalid");
        }
        System.out.println("afjksdhnfklajhsbfajkhlds");
        return true;
    }
}
