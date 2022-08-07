package main.Models.Subjects;

import main.Models.Enums.ActionStatus;
import main.Models.Enums.ActionType;
import main.Models.Enums.GroupType;
import main.Models.Enums.RoleGroupChat;
import main.Models.Interfaces.GroupActions.*;
import main.Models.Stuff.Protocol;

public class PublicGroup extends Group implements InviteGroupAction, UpdateMemberRole, JoinGroupAction, SendGroupCodeAction {
    public PublicGroup(User admin, String groupName) {
        super(admin, groupName);
        super.setGroupType(GroupType.PUBLIC_GROUP);
    }

    @Override
    public Protocol sendInvitationToGroup(User invitor, User user) {
        if (super.getUserInGroup(invitor) != null) {
            Protocol protocol = new Protocol(ActionType.INVITE_JOIN_CHAT);
            protocol.request(invitor, user, super.getGroupId(),"Invite to join");
            protocol.setActionStatus(ActionStatus.WAITING);
            return protocol;
        }
        return null;
    }


    @Override
    public Protocol sendGroupCode(User invitor, User receiver) {
        if (super.getUserInGroup(invitor) != null) {
            Protocol protocol = new Protocol(ActionType.SEND_GROUP_CODE);
            protocol.request(invitor, receiver,super.getGroupId(), this.getGroupCode());
            protocol.setActionStatus(ActionStatus.WAITING);
            return protocol;
        }
        return null;
    }


    @Override
    public boolean updateRoleInGroup(User user, RoleGroupChat role) {
        boolean flag = false;
        if (user != null) {
            if (super.getMembers().contains(user)) {
                if (!user.getRoleInGroupChats().get(super.getGroupId()).equals(role.toString())) {
                    user.getRoleInGroupChats().replace(super.getGroupId(), role.toString());
                    flag = true;
                }
            }
        }
        return flag;
    }

    @Override
    public boolean accept(User user) {
        return super.addMember(user);
    }

    @Override
    public boolean reject() {
        return false;
    }
}
