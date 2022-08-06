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
    public abstract User findUserInGroup(User user);
}
