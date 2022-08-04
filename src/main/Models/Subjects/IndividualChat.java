package main.Models.Subjects;

import main.Models.Enums.GroupType;
import main.Models.Interfaces.GroupActions.JoinGroupAction;

import java.util.*;

public class IndividualChat extends Group implements JoinGroupAction {
    public IndividualChat(User admin, String groupName) {
        super(admin, groupName);
        super.setGroupType(GroupType.INDIVIDUAL);
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

    @Override
    public boolean joinGroup(User user) {
        if (user != null) {
            if (!super.getMembers().contains(user)) {
                super.getMembers().add(user);
                return true;
            }
        }
        return false;
    }
}
