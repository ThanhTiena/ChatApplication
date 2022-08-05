package main.Models.Subjects;

import main.Models.Enums.ActionType;
import main.Models.Enums.GroupType;
import main.Models.Interfaces.GroupActions.JoinGroupAction;
import main.Models.Stuff.Protocol;

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
    public Protocol joinGroup(User user) {
        if (user != null) {
            if (!super.getMembers().contains(user)) {
                super.getMembers().add(user);
                return new Protocol(ActionType.ASK_TO_JOIN_CHAT);
            }
        }
        return null;
    }
}
