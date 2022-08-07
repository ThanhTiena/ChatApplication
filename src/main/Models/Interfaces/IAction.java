package main.Models.Interfaces;

import main.Models.Enums.ActionStatus;
import main.Models.Subjects.User;

public interface IAction<T> {
    abstract T request(User fromUser, User toUser, String content);
    abstract T response(User fromUser, User toUser, ActionStatus response);
}
