package main.Models.Subjects;

import main.Models.Enums.GroupType;
import main.Models.Enums.RoleGroupChat;
import main.Models.Interfaces.GroupActions.JoinGroupAction;
import main.Models.Interfaces.GroupActions.RemoveMembers;
import main.Models.Interfaces.GroupActions.SendGroupCodeAction;
import main.Models.Interfaces.GroupActions.UpdateMemberRole;

import java.util.ArrayList;
import java.util.List;

public class PrivateGroup extends Group implements RemoveMembers, JoinGroupAction, SendGroupCodeAction, UpdateMemberRole {
    public PrivateGroup(User admin, String groupName) {
        super(admin, groupName);
        super.setGroupType(GroupType.PRIVATE_GROUP);
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
    public boolean removeMember(User user) {
        if(user == null || !super.getMembers().contains(user)){
            return false;
        }
        super.getMembers().remove(user);
        return true;
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

    @Override
    public boolean sendGroupCode(String userId) {
        return false;
    }

    @Override
    public boolean updateRoleInGroup(User user, RoleGroupChat role) {

        return false;
    }
}
