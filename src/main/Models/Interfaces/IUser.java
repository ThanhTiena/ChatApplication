package main.Models.Interfaces;

import main.Models.Enums.RequestType;
import main.Models.Subjects.User;

public interface IUser {
    abstract String hashPassword(String rawPassword);
    abstract boolean checkAccount(String userName, String password);
    abstract boolean isFriend(User user);
}
