package main.Models.Interfaces;

import main.Models.Subjects.File;
import main.Models.Subjects.Message;
import main.Models.Subjects.User;

import java.util.List;

public interface IGroup {
    public abstract List<Message> showMessage();

    public abstract List<File> showSentFiles();

    public abstract List<User> showMembers();

    public abstract boolean addMember(User user);
    public abstract boolean removeMember(User user);

    public abstract User getUserInGroup(User user);
    public abstract boolean checkUserJoined(User user);

    public abstract void sendMessage(Message message);

    public abstract void deleteMessage(Message message);

    public abstract void sendFile(File file);

    public abstract void deleteFile(File file);
}
