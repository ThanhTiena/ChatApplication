package main.Models.Subjects;

import main.Models.Enums.GroupStatus;
import main.Models.Enums.GroupType;
import main.Models.Enums.RoleGroupChat;
import main.Models.Interfaces.IGroup;
import main.Ulities.GenerateNumber;

import java.util.*;

public abstract class Group implements IGroup {
    private String groupId;
    private String groupName;
    private GroupType groupType;
    private String groupCode;
    private GroupStatus groupStatus;
    private ArrayList<User> members = new ArrayList<User>();
    private ArrayList<File> files = new ArrayList<File>();
    private ArrayList<Message> messages = new ArrayList<>();

    /* Because in a group chat there may be more than an admin */
    /* So change to List contain user which is defined as an admin*/
    // private User admin;
    private List<User> admins = new ArrayList<User>();

    public Group(User admin, String groupName) {
        this.groupId = GenerateNumber.generateGroupId();
        this.groupName = groupName;
        this.groupCode = GenerateNumber.generateGroupCode();
        this.groupStatus = GroupStatus.Active;

        this.admins.add(admin);
        this.members.add(admin);
    }

    /* ############ GROUP METHOD ################ */
    public abstract List<Message> showMessage();

    public abstract List<File> showSentFiles();

    public abstract List<User> showMembers();

    /* ################################################ */

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public GroupStatus getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(GroupStatus groupStatus) {
        this.groupStatus = groupStatus;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}