package main.Models.Interfaces.GroupActions;

public interface InviteGroupAction {
    abstract boolean sendInvitationToGroup(String userId, String groupId);
}
