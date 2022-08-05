package main.Models.Interfaces;

import main.Models.Subjects.File;
import main.Models.Subjects.Message;
import main.Models.Subjects.User;

import java.util.List;

public interface IGroup {
    public abstract List<Message> showMessage();
    public abstract List<File> showSentFiles();
    public abstract List<User> showMembers();
}
