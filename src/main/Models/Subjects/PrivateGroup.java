package main.Models.Subjects;

import main.Models.Enums.ActionType;
import main.Models.Enums.GroupType;
import main.Models.Enums.RoleGroupChat;
import main.Models.Interfaces.GroupActions.*;
import main.Models.Stuff.Protocol;

import java.util.ArrayList;
import java.util.List;

public class PrivateGroup extends Group implements RemoveMembers, UpdateMemberRole, InviteGroupAction {
    public PrivateGroup(User admin, String groupName) {
        super(admin, groupName);
        super.setGroupType(GroupType.PRIVATE_GROUP);
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

    @Override
    public Protocol sendInvitationToGroup(User user, Group group) {
        return null;
    }
}
