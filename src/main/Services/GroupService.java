package main.Services;

import main.Data.DataStorage;
import main.Models.Enums.GroupType;
import main.Models.Enums.RoleGroupChat;
import main.Models.Stuff.Protocol;
import main.Models.Subjects.*;
import main.Ulities.GroupException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupService {
    private DataStorage dataStorage;
    private Group group;
    private PrivateGroup privateGroup;
    private PublicGroup publicGroup;
    private IndividualChat individualChat;
    private UserService userService;


    public GroupService() {
        this.dataStorage = DataStorage.getInstance();
        this.userService = new UserService();
    }

    public Group findGroupByGroupId(String groupId) {
        return dataStorage.groups.find(group -> group.getGroupId().equals(groupId));
    }

    public Group createGroup(User admin, String groupName, String groupType) throws GroupException {
        try {
            /* group name can be same, so do not check */
            Group group = initGroupChat(admin, groupName, groupType);
            userService.addRoleGroupChat(admin.getUserId(), group.getGroupId(), RoleGroupChat.ADMIN.toString());
            dataStorage.groups.insert(group);
            return group;
        } catch (GroupException exception) {
            throw new GroupException("Create Group Chat Fail");
        }
    }

    public List<User> getGroupMemberDetails(String groupId) {
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        return group.showMembers();
    }

    public List<Message> getGroupMessageDetails(String groupId) {
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        return group.showMessage();
    }

    public List<File> getGroupFileDetails(String groupId) {
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        return group.showSentFiles();
    }

    public boolean sendInvitation(User invitor, User receiver, String groupId) {
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        if (group != null) {
            Protocol protocol = null;
            if (group.getGroupType().equals(GroupType.PUBLIC_GROUP)) {
                publicGroup = (PublicGroup) group;
                protocol = publicGroup.sendInvitationToGroup(invitor, receiver);
            } else {
                privateGroup = (PrivateGroup) group;
                protocol = privateGroup.sendInvitationToGroup(invitor, receiver);
            }
            if (protocol != null) {
                dataStorage.protocols.insert(protocol);
                return true;
            }
        }
        return false;
    }

    public boolean sendGroupCode(User invitor, User receiver, String groupId) {
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        if (group != null && group.getGroupType().equals(GroupType.PUBLIC_GROUP)) {
            publicGroup = (PublicGroup) group;
            Protocol protocol = publicGroup.sendGroupCode(invitor, receiver);
            if (protocol != null) {
                dataStorage.protocols.insert(protocol);
                return true;
            }
        }
        return false;
    }

    public boolean responseRequest(User user, String groupId, String action) throws GroupException {
        List<Protocol> protocols = dataStorage.protocols.get(p -> p.getReceiver().getUserId()
                        .equals(user.getUserId()))
                .stream().toList();
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        for (Protocol protocol : protocols) {
            boolean condition = protocol.getGroupId().equals(groupId) &&
                    protocol.getReceiver().getUserId().equals(user.getUserId());
            if (condition) {
                if (action.equalsIgnoreCase("accept")) {
                    acceptResponse(group, user);
                    dataStorage.protocols.delete(protocol);
                    break;
                } else if (action.equalsIgnoreCase("reject")) {
//                    rejectReponse(group);
                    dataStorage.protocols.delete(protocol);
                    break;
                } else {
                    throw new GroupException("Only Accept or Reject");
                }
            }
        }
        return false;
    }

    private boolean acceptResponse(Group group, User user) {
        boolean isAccept = false;
        if (group.getGroupType().equals(GroupType.PRIVATE_GROUP)) {
            privateGroup = (PrivateGroup) group;
            isAccept = privateGroup.accept(user);
        } else {
            publicGroup = (PublicGroup) group;
            isAccept = publicGroup.accept(user);
        }
        return isAccept;
    }

//    private boolean rejectReponse(Group group) {
//        return group.getGroupType().equals(GroupType.PUBLIC_GROUP) ?
//                ((PublicGroup) group).reject() : ((PrivateGroup) group).reject();
//    }

    public boolean changeUserRoleInGroup(User user, String groupId, RoleGroupChat role) {
        boolean isUpdate = false;
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        if (!group.getGroupType().equals(GroupType.INDIVIDUAL)) {
            if (group.getGroupType().equals(GroupType.PUBLIC_GROUP)) {
                publicGroup = (PublicGroup) group;
                isUpdate = publicGroup.updateRoleInGroup(user, role);
            } else {
                privateGroup = (PrivateGroup) group;
                isUpdate = privateGroup.updateRoleInGroup(user, role);
            }
        }
        return isUpdate;
    }

    public boolean inviteToJoinGroup(User invitor, User user, String groupId) throws GroupException {
        boolean flag = false;
        group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        if (group.getGroupType().equals(GroupType.INDIVIDUAL)) {
            individualChat = (IndividualChat) group;
            individualChat.addMember(user);
            flag = true;
        } else if (group.getGroupType().equals(GroupType.PRIVATE_GROUP)) {
            privateGroup = (PrivateGroup) group;
            if (!invitor.getRoleInGroupChats().get(groupId).equals(RoleGroupChat.ADMIN)) {
                throw new GroupException("This Group need to administrator's permission to join");
            }
            privateGroup.addMember(user);
            flag = true;
        } else {
            publicGroup = (PublicGroup) group;
            publicGroup.addMember(user);
            flag = true;
        }
        return flag;
    }

    public boolean joinGroupByCode(String groupCode, String groupId, User user) throws GroupException {
        boolean flag = false;
        group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        if (group != null) {
            if (!group.getGroupType().equals(GroupType.PUBLIC_GROUP)) {
                throw new GroupException("Action only valid with public group");
            }
            if (group.getGroupCode().equals(groupCode)) {
                publicGroup = (PublicGroup) group;
                publicGroup.addMember(user);
                flag = true;
            }
        }
        return flag;
    }

    public List<String> getGroupsOfUser(User user) {
        List<String> groups = new ArrayList<>();
        for (Group group : dataStorage.groups.findAll()) {
            if (group.findUserInGroup(user) != null) {
                groups.add(group.getGroupId());
            }
        }
        return groups;
    }

    public List<String> getContactsOfUser(User user) {
        List<Message> messages = dataStorage.messages.get(
                m -> m.getReceiverId().equals(user.getUserId()) ||
                        m.getSenderId().equals(user.getUserId()));
        List<String> messageIds = new ArrayList<>();
        messages.forEach(message -> {
            messageIds.add(message.getMessageId());
        });
        return messageIds;
    }

    public List<String> getConversations(User user) {
        List<String> userJoinedGroups = getGroupsOfUser(user);
        List<String> userContacts = getContactsOfUser(user);

        List<String> conservations = Stream.concat(userJoinedGroups.stream(), userContacts.stream())
                .collect(Collectors.toList());
        return conservations;
    }

    public boolean removeUserFromGroup(String userId, String groupId) {
        if (userId.equals("") || groupId.equals("")) {
            return false;
        }
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        User user = dataStorage.users.find(u -> u.getUserId().equals(userId));
        return group.removeMember(user);
    }

    private Group initGroupChat(User admin, String groupName, String groupType) throws GroupException {
        switch (groupType) {
            case ("PRIVATE_GROUP"):
                return new PrivateGroup(admin, groupName);
            case ("PUBLIC_GROUP"):
                return new PublicGroup(admin, groupName);
            case ("INDIVIDUAL"):
                return new IndividualChat(admin, ""); /* Because individual chat can not have group name*/
            default:
                throw new GroupException("Group Type Invalid, Initial Group Chat Failed");
        }
    }
}
