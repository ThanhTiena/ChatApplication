package main.Models.Subjects;

import main.Models.Enums.ActionType;
import main.Models.Enums.GroupType;
import main.Models.Enums.RoleGroupChat;
import main.Models.Interfaces.GroupActions.JoinGroupAction;
import main.Models.Interfaces.GroupActions.RemoveMembers;
import main.Models.Interfaces.GroupActions.SendGroupCodeAction;
import main.Models.Interfaces.GroupActions.UpdateMemberRole;
import main.Models.Stuff.Protocol;

import java.util.ArrayList;
import java.util.List;

public class PrivateGroup extends Group implements RemoveMembers, SendGroupCodeAction, UpdateMemberRole {
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
    public Protocol sendGroupCode(User sender, User receiver) {
        Protocol protocol = new Protocol(ActionType.SEND_GROUP_CODE);
        protocol.request(sender,receiver,this.getGroupCode());
        return protocol;
    }

    @Override
    public boolean updateRoleInGroup(User user, RoleGroupChat role) {
        boolean flag = false;
        if(user != null){
            if(super.getMembers().contains(user)){
                if(!user.getRoleInGroupChats().get(super.getGroupId()).equals(role.toString())){
                    user.getRoleInGroupChats().replace(super.getGroupId(),role.toString());
                    flag = true;
                }
            }
        }
        return flag;
    }
}
