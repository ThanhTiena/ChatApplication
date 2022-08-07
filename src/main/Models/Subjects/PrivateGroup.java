package main.Models.Subjects;

import main.Models.Enums.ActionStatus;
import main.Models.Enums.ActionType;
import main.Models.Enums.GroupType;
import main.Models.Enums.RoleGroupChat;
import main.Models.Interfaces.GroupActions.*;
import main.Models.Stuff.Protocol;

public class PrivateGroup extends Group implements UpdateMemberRole, InviteGroupAction, JoinGroupAction {
    public PrivateGroup(User admin, String groupName) {
        super(admin, groupName);
        super.setGroupType(GroupType.PRIVATE_GROUP);
    }

    @Override
    public boolean updateRoleInGroup(User user, RoleGroupChat role) {
        boolean flag = false;
        if (user != null) {
            if (super.getUserInGroup(user) != null) {
                if (!user.getRoleInGroupChats()
                        .get(super.getGroupId()).equals(role.toString())) {
                    user.getRoleInGroupChats().replace(super.getGroupId(), role.toString());
                    flag = true;
                }
            }
        }
        return flag;
    }

    @Override
    public Protocol sendInvitationToGroup(User invitor, User user) {
        if (super.getUserInGroup(invitor) != null) {
            if (invitor.getRoleInGroupChats().get(super.getGroupId()).equals(RoleGroupChat.ADMIN)) {
                Protocol protocol = new Protocol(ActionType.INVITE_JOIN_CHAT);
                protocol.request(invitor, user, super.getGroupId(), "Invite to join");
                protocol.setActionStatus(ActionStatus.WAITING);
                return protocol;
            }
        }
        return null;
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
