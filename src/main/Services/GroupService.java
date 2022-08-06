package main.Services;

import main.Data.DataStorage;
import main.Models.Enums.GroupType;
import main.Models.Enums.RoleGroupChat;
import main.Models.Subjects.*;
import main.Ulities.GroupException;

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
    }

    public Group findGroupByGroupId(String groupId) {
        return dataStorage.groups.find(group -> group.getGroupId().equals(groupId));
    }

    public boolean createChat(User admin, String groupName, String groupType) throws GroupException {
        try {
            /* group name can be same, so do not check */
            Group group = initGroupChat(admin, groupName, groupType);
            userService.addRoleGroupChat(admin.getUserId(),group.getGroupId(), RoleGroupChat.ADMIN.toString());
            dataStorage.groups.insert(group);
            return true;
        } catch (GroupException exception) {
            throw new GroupException("Create Group Chat Fail");
        }
    }

    public void groupMemberDetails(String groupId) {
        stringBuilder = new StringBuilder();
        dataStorage.groups.find(group -> group.getGroupId().equals(groupId))
                .showMembers()
                .stream()
                .forEach(member -> {
                    stringBuilder.append(member.getFullName()).append("\n");
                });
        System.out.println(stringBuilder);
    }

    public void groupMessageDetails(String groupId) {
        stringBuilder = new StringBuilder();
        dataStorage.groups.find(group -> group.getGroupId().equals(groupId))
                .showMessage()
                .stream()
                .forEach(message -> {
                    stringBuilder.append(message.getSender().getFullName());
                    stringBuilder.append(message.getContent());
                    stringBuilder.append(message.getSentAt());
                    stringBuilder.append("\n");
                });
        System.out.println(stringBuilder);
    }

    public void groupFileDetails(String groupId) {
        stringBuilder = new StringBuilder();
        dataStorage.groups.find(group -> group.getGroupId().equals(groupId))
                .showSentFiles()
                .stream()
                .forEach(file -> {
                    stringBuilder.append(file.getFileName());
                    stringBuilder.append(file.getCreatedAt());
                    stringBuilder.append("\n");
                });
        System.out.println(stringBuilder);
    }

    public boolean addMember(User invitor, User user, String groupId) throws GroupException {
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

    public boolean joinGroupByCode(String groupCode, String groupId,User user) {
        boolean flag = false;
        group = dataStorage.groups.find(g -> g.getGroupId().equals(groupId));
        if (group != null) {
            if(group.getGroupCode().equals(groupCode)){
                publicGroup = (PublicGroup) group;
                publicGroup.addMember(user);
                flag = true;
            }
        }
        return flag;
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
