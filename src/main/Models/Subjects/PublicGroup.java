package main.Models.Subjects;

import main.Models.Enums.ActionType;
import main.Models.Enums.GroupType;
import main.Models.Enums.RoleGroupChat;
import main.Models.Interfaces.GroupActions.*;
import main.Models.Stuff.Protocol;

import java.util.ArrayList;
import java.util.List;

public class PublicGroup extends Group implements InviteGroupAction, UpdateMemberRole, JoinGroupAction, SendGroupCodeAction {
    public PublicGroup(User admin, String groupName) {
        super( admin, groupName);
        super.setGroupType(GroupType.PUBLIC_GROUP);
    }

    @Override
    public Protocol sendInvitationToGroup(User user, Group group) {
        Protocol protocol = new Protocol(ActionType.INVITE_JOIN_CHAT);
        protocol.request(super.getAdmins().get(0),user,"Invite to join");
        return protocol;
    }

    @Override
    public Protocol requestForJoiningGroup(User user) {
        if (user != null) {
            if (super.findUserInGroup(user) != null) {
//                super.getMembers().add(user);
                Protocol protocol = new Protocol((ActionType.ASK_TO_JOIN_CHAT));
                protocol.request(user,super.getAdmins().get(0),"Join group");
                return protocol;
            }
        }
        return null;
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
