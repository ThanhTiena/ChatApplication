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
    public boolean addMember(User user) {
        if (super.getMembers().size() < 2) {
            super.addMember(user);
            return true;
        }
        return false;
    }

    @Override
    public Protocol requestForJoiningGroup(User user) {
        if (user != null) {
            if (super.findUserInGroup(user) != null) {
//                super.getMembers().add(user);
                return new Protocol(ActionType.ASK_TO_JOIN_CHAT);
            }
        }
        return null;
    }
}
