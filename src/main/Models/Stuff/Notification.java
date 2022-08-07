package main.Models.Stuff;

import main.Models.Enums.ActionStatus;
import main.Models.Interfaces.IAction;
import main.Models.Subjects.Message;
import main.Models.Subjects.User;

import java.util.Locale;

public class Notification implements IAction<Message> {
    private String content;
    private Message message;

    @Override
    public Message request(User fromUser, User toUser, String groupId, String content) {
        content = "You have send a " + content.toLowerCase(Locale.ROOT);
        content += " to " + toUser.getFullName() + "\n";
        content += "Created by " + fromUser.getFullName();

        return new Message(fromUser.getUserId(), toUser.getUserId(), content);
    }

    @Override
    public Message response(User fromUser, User toUser, ActionStatus response) {
        content = "Your " + this.message.getContent();
        content += " have been " + response.toString() + " by " + toUser + "\n";
        content += "Created by " + fromUser.getFullName();
        return new Message(fromUser.getUserId(), toUser.getUserId(), content);
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
