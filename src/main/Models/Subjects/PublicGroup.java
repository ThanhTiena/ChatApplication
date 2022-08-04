package main.Models.Subjects;

import main.Models.Enums.RoleGroupChat;

import java.util.ArrayList;
import java.util.List;

public class PublicGroup extends Group {
    public PublicGroup(User admin, String groupName) {
        super( admin, groupName);
    }

    @Override
    public List<Message> showMessage() {
        return super.getMessages();
    }

    @Override
    public List<File> showSentFiles() {
        return super.getFiles();
    }

    @Override
    public List<User> showMembers() {
        return super.getMembers();
    }
}
