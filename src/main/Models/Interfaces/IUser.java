package main.Models.Interfaces;

import main.Models.Enums.RequestType;

public interface IUser {
    public abstract boolean sendMessage();
    public abstract boolean sendRequest(RequestType type);
}
