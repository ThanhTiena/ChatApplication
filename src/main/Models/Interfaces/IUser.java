package main.Models.Interfaces;

import main.Models.Enums.RequestType;

public interface IUser {
    abstract String hashPassword(String rawPassword);

    abstract boolean checkAccount(String userName, String password);
}
