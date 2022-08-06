package main.Models.Stuff;

import main.Models.Interfaces.IAction;
import main.Models.Subjects.Message;
import main.Models.Subjects.User;

public class Notification implements IAction<Message> {
    private String content;

    @Override
    public Message request(User fromUser, User toUser, Object entity) {
        Message message = (Message) entity;
        content = "You have send a " + message.getContent() + " to " + toUser.getFullName() + "\n";
        content = "Created by " + fromUser.getFullName();
        System.out.println(content);
        return message;
    }

    @Override
    public Message response(User fromUser, User toUser, Object entity) {
        Message message = (Message) entity;
        content = "Your " + message.getContent() + " have been executed by " + toUser + "\n";
        content = "Created by " + fromUser.getFullName();
        System.out.println(content);
        return message;
    }
}
