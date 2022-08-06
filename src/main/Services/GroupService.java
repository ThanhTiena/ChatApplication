package main.Services;

import main.Data.DataStorage;
import main.Models.Enums.GroupType;
import main.Models.Enums.RoleGroupChat;
import main.Models.Subjects.*;
import main.Ulities.GenerateNumber;
import main.Ulities.GroupException;

import java.util.ArrayList;
import java.util.Arrays;

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

    public Group findGroupByGroupName(String groupName) {
        return dataStorage.groups.find(group -> group.getGroupName().equals(groupName));
    }

    public boolean createChat(User admin, String groupName, String groupType) throws GroupException {
        try {
            /* group name can be same, so do not check */
            Group group = initGroupChat(admin, groupName, groupType);
            userService.addRoleGroupChat(admin.getUserId(), RoleGroupChat.ADMIN.toString());
            dataStorage.groups.insert(group);
            return true;
        } catch (GroupException exception) {
            throw new GroupException("Create Group Chat Fail");
        }
    }

    public void groupMemberDetails(String groupId) {
        stringBuilder = new StringBuilder();
        group.showMembers().stream().forEach(member -> {
            stringBuilder.append(member.getFullName()).append("\n");
        });
        System.out.println(stringBuilder);
    }

    public boolean addMember(User invitor, User user, String groupName) {
        boolean flag = false;
        if (!groupName.equals("")) {

        }
        if (invitor != null) {

        }
        return flag;
    }
    public User checkUserIsAdmin(String userId){

        return null;
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
