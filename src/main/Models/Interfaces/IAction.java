package main.Models.Interfaces;

import main.Models.Subjects.User;

public interface IAction<T> {
    abstract T request(User fromUser, User toUser, Object entity);
    abstract T response(User fromUser, User toUser, Object entity);
}
