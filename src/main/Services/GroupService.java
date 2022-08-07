package main.Services;

import main.Data.DataStorage;
import main.Models.Enums.GroupType;
import main.Models.Enums.RoleGroupChat;
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

    private static StringBuilder stringBuilder;

    public GroupService() {
        this.dataStorage = DataStorage.getInstance();
        this.userService = new UserService();
    }

    public Group findGroupByGroupId(String groupId) {
        return dataStorage.groups.find(group -> group.getGroupId().equals(groupId));
    }

    public Group createChat(User admin, String groupName, String groupType) throws GroupException {
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

    public List<User> groupMemberDetails(String groupId) {
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        return group.showMembers();
    }

    public List<Message> groupMessageDetails(String groupId) {
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        return group.showMessage();
    }

    public List<File> groupFileDetails(String groupId) {
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        return group.showSentFiles();
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
                m -> m.getReceiverId().equals(user.getUserId()),
                m -> m.getSenderId().equals(user.getUserId()));
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
